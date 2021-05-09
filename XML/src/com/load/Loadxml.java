package com.load;

import com.Engine.EngineInterface;
import com.Engine.MainEngine;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.Generated.RizpaStockExchangeDescriptor;
import com.Generated.RseItem;
import com.Generated.RseStock;
import com.Generated.RseUser;
import com.User.User;
import com.stock.Stock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class Loadxml {

    public static EngineInterface ParseXml(File file) throws JAXBException, FileNotFoundException, StockException, Myexception {
        JAXBContext jaxbContext=JAXBContext.newInstance("com.Generated");
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSE =(RizpaStockExchangeDescriptor) unmarshaller.unmarshal(file);

        return createMainEngine(RSE);

    }

    public static MainEngine createMainEngine(RizpaStockExchangeDescriptor rse) throws StockException, Myexception {//this method pass all data from Generated classes to work class Mainengine
        MainEngine mainEngine=new MainEngine();
        for(RseStock stock: rse.getRseStocks().getRseStock()){ //copy the stocks data
            mainEngine.addStock(new Stock(stock.getRseSymbol(), stock.getRseCompanyName(), stock.getRsePrice()));
        }

        for(RseUser user: rse.getRseUsers().getRseUser()){ //copy the users data
            User newUser= new User(user.getName());
            for(RseItem item:user.getRseHoldings().getRseItem()){
                Stock stock=mainEngine.getStockByName(item.getSymbol());
                if(stock==null){
                    throw new Myexception("The stock : "+item.getSymbol()+ " at User :"+user.getName()+" Is not exist in the system");
                }
                if(item.getQuantity()<=0){
                    throw new Myexception("The quantity of stock :"+ item.getSymbol()+" at user"+ user.getName() +" is less or equal to zero");
                }
                newUser.addHoldings(stock, item.getQuantity());
            }
            mainEngine.addUser(newUser);
        }
        return mainEngine;
    }

}
