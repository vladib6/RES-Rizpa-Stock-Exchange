package com.stock;

import com.CommandDTO;
import com.Engine.Myexception;
import com.StockDTO;
import com.TransactionDTO;

import java.util.*;

public class Allstocks {
    public Allstocks() {
        allStocks=new HashMap<>();
    }

    private HashMap<String,Stock> allStocks; //key=symbol

    public HashMap<String,Stock> getAllStocks(){ return allStocks; }

    public Stock getStockByName(String symbol){
        return allStocks.get(symbol);
    }

    synchronized public boolean addStock(String companyName,String symbol,int price){
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

    synchronized public boolean isCompanyNameExist(String cName){//check if already exist stock with that company name
        boolean res=false;
        for(Map.Entry<String,Stock> entry: allStocks.entrySet()){
            if(entry.getValue().getCompanyName().equals(cName)){
                res=true;
            }
        }
        return res;
    }
    synchronized public boolean isStockexist(String symbol){
        return allStocks.containsKey(symbol);
    }

    public List<StockDTO> getStocksDTO (){
        List<StockDTO> res=new ArrayList<>();
        for(Map.Entry<String,Stock> entry: allStocks.entrySet()){
            res.add(entry.getValue().createStockDto());
        }
        return res;
    }

    synchronized public boolean addNewStock(String name,String symbol,int price) throws Myexception {
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


    public LinkedList<TransactionDTO> createTransactionsDtoList(String stockname){ return allStocks.get(stockname).createTransactionDTOlist(); }

    public List<CommandDTO> getSellCommands(String stockname){return allStocks.get(stockname).getSellCommandsDto();}
    public List<CommandDTO> getBuyCommands(String stockname){return allStocks.get(stockname).getBuyCommandsDto();}

}

