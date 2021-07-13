package com.stock;

import com.Engine.Myexception;
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

    public Stock getStockByName(String symbol){
        return allStocks.get(symbol);
    }

    public boolean addStock(String companyName,String symbol,int price){
        if (!allStocks.containsKey(symbol)){
            if(isCompanyNameExist(companyName)){
                return false;
            }
            allStocks.put(symbol.toUpperCase(),new Stock(symbol, companyName, price));
            return true;
        }else{
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
    public boolean isStockexist(String symbol){
        return allStocks.containsKey(symbol);
    }

    public List<StockDTO> getStocksDTO (){
        List<StockDTO> res=new ArrayList<>();
        for(Map.Entry<String,Stock> entry: allStocks.entrySet()){
            res.add(entry.getValue().createStockDto());
        }
        return res;
    }

    public boolean addNewStock(String name,String symbol,int price) throws Myexception {
        if(isStockexist(symbol)){
            throw new Myexception("Duplicate stock symbol");
        }else if(isCompanyNameExist(name)){
            throw new Myexception("Duplicate Company name");
        }else if(price<=0){
            throw new Myexception("Negative Price is not valid");
        }else{
            allStocks.put(symbol.toUpperCase(),new Stock(symbol,name,price));
            return true;
        }
    }




}

