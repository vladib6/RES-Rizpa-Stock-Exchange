package com.Engine;

import com.Command.WaitingCommands.AllWaitingcommands;
import com.Transaction.Alltransactions;
import com.Transaction.Transaction;
import com.stock.*;

public class MainEngine {

    public MainEngine(){
        allWaitingcommands=new AllWaitingcommands();
        allStocks= new Allstocks();
        allTransactions= new Alltransactions();
    }
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

    public void addStock(Stock stock) throws Myexception {
        if(allStocks.getAllStocks().containsKey(stock.getSymbol()) || allStocks.isCompanyNameExist(stock.getCompanyName())){
            throw new Myexception("You can't create two stocks with the same Symbol or company name !");
        }else{
            allStocks.addStock(stock);
            allTransactions.addCellToHashMap(stock.getSymbol(),stock.getTransactionsList());
            allWaitingcommands.addCellToHashMap(stock.getSymbol(),stock.getBuyWaitinglist(),stock.getSellWaitinglist());
        }

    }

    public void addTransaction(String symbol, Transaction transaction){
        allTransactions.addTransaction(symbol, transaction);
    }

    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }



}
