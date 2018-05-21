package com.example.ExampleCamel.Mq.Model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = ",", skipFirstLine = true)
public class Student implements Serializable {

    @XmlAttribute
    @DataField(pos = 1)
    private int userId;
    @XmlAttribute
    @DataField(pos =2)
    private int active;
    @XmlAttribute
    @DataField(pos =3)
    private String email;
    @XmlAttribute
    @DataField(pos = 4)
    private String lastName;
    @XmlAttribute
    @DataField(pos = 5)
    private String name;
    @XmlAttribute
    @DataField(pos = 6)
    private String password;
}

