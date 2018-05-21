package com.example.ExampleCamel.Mq.service;

import com.example.ExampleCamel.Mq.Model.Student;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;

/**
 * Created by Palko on 17/05/2018.
 */
public class ConvertorRoute implements RoutesBuilder {

    @Override
    public void addRoutesToCamelContext(CamelContext context) throws Exception {
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                try {
                    DataFormat bindy = new BindyCsvDataFormat(Student.class);
                    from("file:C:\\Users\\Palko\\Desktop\\ExampleCamel&Mq\\src\\main\\java\\com\\example\\ExampleCamel\\Mq\\Files").
                            unmarshal(bindy).
                            marshal().
                            xstream().
                            to("file:C:\\Users\\Palko\\Desktop\\ExampleCamel&Mq\\src\\main\\java\\com\\example\\ExampleCamel\\Mq\\Output?fileName=user.xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}