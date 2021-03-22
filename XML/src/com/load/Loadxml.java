package com.load;

import com.Engine.EngineInterface;
import com.Engine.MainEngine;
import com.Engine.StockException;
import com.Generated.RizpaStockExchangeDescriptor;
import com.Generated.RseStock;
import com.User.User;
import com.stock.Stock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Loadxml {//TODO: delete the connect user in phase 3

    public static EngineInterface ParseXml(String filepath) throws JAXBException, FileNotFoundException, StockException {
        InputStream inputStream = new FileInputStream(new File(filepath));
        JAXBContext jaxbContext=JAXBContext.newInstance("com.Generated");
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSE =(RizpaStockExchangeDescriptor) unmarshaller.unmarshal(new File(filepath));

        return createMainEngine(RSE);

    }

    public static MainEngine createMainEngine(RizpaStockExchangeDescriptor rse) throws StockException {//this method pass all data from Generated classes to work class Mainengine
        MainEngine mainEngine=new MainEngine();
        for(RseStock stock: rse.getRseStocks().getRseStock()){
            mainEngine.addStock(new Stock(stock.getRseSymbol(), stock.getRseCompanyName(), stock.getRsePrice()));
        }

        mainEngine.Connect(new User("vladi"));
        return mainEngine;
    }

}
