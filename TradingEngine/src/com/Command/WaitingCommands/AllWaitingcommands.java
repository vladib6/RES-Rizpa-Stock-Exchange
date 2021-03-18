package com.Command.WaitingCommands;



import java.util.HashMap;

public class AllWaitingcommands {
    public AllWaitingcommands(){
        buyWaitinglistMap=new HashMap<>();
        sellWaitinglistMap=new HashMap<>();
    }

   private HashMap<String,BuyWaitinglist> buyWaitinglistMap;
   private HashMap<String,SellWaitinglist> sellWaitinglistMap;


    public void addCellToHashMap(String symbol,BuyWaitinglist bl,SellWaitinglist sl){
        buyWaitinglistMap.put(symbol,bl);
        sellWaitinglistMap.put(symbol,sl);

    }
}
