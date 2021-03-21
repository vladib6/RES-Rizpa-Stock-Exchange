package com.load;

import com.Engine.EngineInterface;
import com.Engine.MainEngine;
import com.Engine.StockException;
import com.User.User;
import com.stock.Stock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class Loadxml {

    public static EngineInterface ParseXml(String filepath) throws JAXBException, FileNotFoundException, StockException {
        JAXBContext jaxbContext=JAXBContext.newInstance(MainEngine.class);
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        MainEngine mainEngine=(MainEngine) unmarshaller.unmarshal(new File(filepath));
        for(Stock stock: mainEngine.getStockArray().getArrayList()){//pass allstocks from Stockarray.class to Allstocks.class
            mainEngine.addStock(stock);
        }
        mainEngine.Connect(new User("vladi"));
        return mainEngine;
    }

}
