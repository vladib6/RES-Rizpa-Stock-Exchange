package com.stock;

import java.util.HashMap;
import java.util.Map;

public class Allstocks {
    public Allstocks() {
        allStocks=new HashMap<String,Stock>();
    }

    HashMap<String,Stock> allStocks;

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





}

