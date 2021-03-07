package com.Command;

import java.util.HashMap;

public class AllWaitingcommands {
    public AllWaitingcommands(){
        buyWaitinglistMap=new HashMap<String,BuyWaitinglist>();
        sellWaitinglistMap=new HashMap<String,SellWaitinglist>();
    }

   private HashMap<String,BuyWaitinglist> buyWaitinglistMap;
   private HashMap<String,SellWaitinglist> sellWaitinglistMap;

    public BuyWaitinglist getBuyList(String symbol){
      return buyWaitinglistMap.get(symbol);
    }

    public SellWaitinglist getSellList(String symbol){
        return sellWaitinglistMap.get(symbol);
    }

    public void addBuylist(String symbol,BuyWaitinglist buyWaitinglist){
        this.buyWaitinglistMap.put(symbol, buyWaitinglist);
    }

    public void addSelllist(String symbol,SellWaitinglist sellWaitinglist){
        this.sellWaitinglistMap.put(symbol, sellWaitinglist);
    }

    public void addWaiitngBuycmd(String symbol,Command command){
        buyWaitinglistMap.get(symbol).addCmdToList(command);
    }

    public void addWaitingSellcmd(String symbol,Command command){
        sellWaitinglistMap.get(symbol).addCmdToList(command);
    }
}
