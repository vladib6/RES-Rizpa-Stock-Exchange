package com.stock;

import com.StockDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Allstocks {
    public Allstocks() {
        allStocks=new HashMap<>();
    }

    private HashMap<String,Stock> allStocks; //key=symbol

    public HashMap<String,Stock> getAllStocks(){ return allStocks; }

    public Stock getStockByName(String stockname){
        return allStocks.get(stockname);
    }

    public boolean addStock(String companyName,String symbol,int price){
        if(isCompanyNameExist(companyName)){
            return false;
        }else{
            if (!allStocks.containsKey(symbol)){
                allStocks.put(symbol,new Stock(symbol, companyName, price));
            }
            return true;
        }
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

    public List<StockDTO> getStocksDTO (){
        List<StockDTO> res=new ArrayList<>();
        for(Map.Entry<String,Stock> entry: allStocks.entrySet()){
            res.add(entry.getValue().createStockDto());
        }
        return res;
    }




}

