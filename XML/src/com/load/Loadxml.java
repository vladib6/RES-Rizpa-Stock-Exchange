package com.load;

import com.Engine.EngineInterface;
import com.Engine.MainEngine;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.Generated.RizpaStockExchangeDescriptor;
import com.Generated.RseItem;
import com.Generated.RseStock;
import com.User.Trader;
import com.User.Traderinterface;
import com.stock.Stock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Loadxml {

    public static void ParseXml(InputStream inputStream,EngineInterface engineInterface,String username) throws JAXBException, FileNotFoundException, StockException, Myexception {
        JAXBContext jaxbContext=JAXBContext.newInstance("com.Generated");
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSE =(RizpaStockExchangeDescriptor) unmarshaller.unmarshal(inputStream);
        addDataToEngine(engineInterface,RSE,username);
    }

    public static void addDataToEngine(EngineInterface engineInterface,RizpaStockExchangeDescriptor RSE,String usename) throws Myexception {
                if(isValidXml(RSE.getRseHoldings().getRseItem(),RSE.getRseStocks().getRseStock())){
                    List<RseStock> rseStocks= RSE.getRseStocks().getRseStock();
                    for(RseStock stock:rseStocks){
                        if(!engineInterface.addStock(stock.getRseCompanyName(), stock.getRseSymbol(), stock.getRsePrice())){
                            throw new Myexception("you try to load stock with duplicate company name");
                        }
                    }
                    List<RseItem> rseItems=RSE.getRseHoldings().getRseItem();
                    Traderinterface traderinterface= engineInterface.getTrader(usename);
                    for(RseItem item:rseItems){
                        traderinterface.addHoldings(engineInterface.getStockByName(item.getSymbol()), item.getQuantity());
                    }

                }else{
                    throw new Myexception("XML File are not valid,you try load your own holdings with stock that no exist in stocks lisy");
                }
    }

    public static boolean isValidXml(List<RseItem> rseItems,List<RseStock> rseStocks){
            boolean res=true;

            for(RseItem item: rseItems){
                if(!isItemInStocks(item,rseStocks)){
                    res=false;
                }
            }
            return res;

    }

    public  static boolean isItemInStocks(RseItem item,List<RseStock> rseStocks){
        boolean res=false;
            for(RseStock stock:rseStocks){
                if(item.getSymbol().equals(stock.getRseSymbol())){
                    res=true;
                }
            }

        return  res;
    }


}
