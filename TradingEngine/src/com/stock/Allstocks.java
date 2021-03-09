package com.stock;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
public class Allstocks {
    public Allstocks() {
        allStocks=new HashMap<String,Stock>();
    }

    private HashMap<String,Stock> allStocks;

    public HashMap<String,Stock> getAllStocks(){ return allStocks; }

    public Stock getStockByName(String stockname){
        return allStocks.get(stockname);
    }

    public void addStock(Stock stock){
        allStocks.put(stock.getSymbol(),stock);
    }

    public void removeStock(String symbol){
        allStocks.remove(symbol);
    }

    public boolean isCompanyNameExist(String cName){//check if already exist stock with that company name
        boolean res=false;
        for(Map.Entry<String,Stock> entry: allStocks.entrySet()){
            if(entry.getValue().getCompanyName().equals(cName)){
                res=true;
            }
        }
        return res;
    }




}

