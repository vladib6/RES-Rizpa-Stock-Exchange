package com.Engine;

import com.Command.WaitingCommands.AllWaitingcommands;
import com.Transaction.Alltransactions;
import com.Transaction.Transaction;
import com.stock.*;

public class MainEngine {

   private AllWaitingcommands allWaitingcommands;
   private Allstocks allStocks;
   private Alltransactions allTransactions;


    public AllWaitingcommands getAllWaitingcommands() {
        return allWaitingcommands;
    }

    public Allstocks getAllStocks() {
        return allStocks;
    }

    public Alltransactions getAllTransactions() {
        return allTransactions;
    }

    public void addStock(Stock stock){
        allStocks.addStock(stock);
    }

    public void addTransaction(String symbol, Transaction transaction){
        allTransactions.addTransaction(symbol, transaction);
    }

    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }


}
