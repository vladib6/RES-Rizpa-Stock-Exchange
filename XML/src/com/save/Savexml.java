package com.save;

import com.Engine.MainEngine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Savexml {

    public static void save(MainEngine mainEngine) throws JAXBException {//TODO
        try{
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(MainEngine.class);
            //creating the marshaller object
            Marshaller marshallObj = jContext.createMarshaller();
            //setting the property to show xml format output
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //setting the values in POJO class
            //calling the marshall method
            OutputStream os=new FileOutputStream("save.xml");
            marshallObj.marshal(mainEngine, os);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
