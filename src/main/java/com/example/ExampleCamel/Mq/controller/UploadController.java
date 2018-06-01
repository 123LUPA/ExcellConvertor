package com.example.ExampleCamel.Mq.controller;

import com.example.ExampleCamel.Mq.service.ConvertorRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:\\Users\\Palko\\Desktop\\ExcellConvertor\\src\\main\\java\\com\\example\\ExampleCamel\\Mq\\Files\\";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            CamelContext context = new DefaultCamelContext();
            context.start();
            convertFromCSVtoXML(context);
            sendData(context);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


    public void sendData(CamelContext context) throws Exception {
        context.addRoutes(new RouteBuilder()
        {
            @Override
            public void configure() throws Exception
            {
                from("file:src\\main\\java\\com\\example\\ExampleCamel\\Mq\\Output?fileName=user.xml").to("activemq:user");
            }

        });

    }

    public void convertFromCSVtoXML(CamelContext context){
        try{
            ConvertorRoute route = new ConvertorRoute();
            route.addRoutesToCamelContext(context);

        }catch(Exception exe){
            exe.printStackTrace();
        }
    }

}