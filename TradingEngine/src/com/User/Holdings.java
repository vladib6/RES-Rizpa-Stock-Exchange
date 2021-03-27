package com.User;

import com.HoldingsDTO;
import com.stock.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Holdings {//TODO : think how save stocks+quantity + current price

    public Holdings(){
        holdingsMap=new HashMap<>();
    }
    private Map<Stock,Integer> holdingsMap;

    public int currentHoldings(){
        int res=0;
        for( Map.Entry<Stock,Integer> entry : holdingsMap.entrySet()){
            res+=entry.getKey().getCurrentPrice()*entry.getValue();
        }

        return res;
    }

    public void addToHoldings(Stock stock,int quantity) {
        if (holdingsMap.containsKey(stock)) {
            holdingsMap.put(stock, holdingsMap.get(stock) + quantity);
        } else {
            holdingsMap.put(stock, quantity);
        }
    }

    public void removeFromHoldings(Stock stock, int quantity){
        holdingsMap.put(stock, holdingsMap.get(stock) -quantity);

        if(holdingsMap.get(stock)<=0){//if the quantity is zero so remove the stock from the holdings
            holdingsMap.remove(stock);
        }
    }

    public ArrayList<HoldingsDTO> createDTO(){
        ArrayList<HoldingsDTO> res = new ArrayList<>();
        for(Map.Entry<Stock,Integer> entry : holdingsMap.entrySet()){
            res.add(new HoldingsDTO(entry.getValue(),entry.getKey().getSymbol(),entry.getKey().getCurrentPrice()));
        }
        return res;
    }
}
