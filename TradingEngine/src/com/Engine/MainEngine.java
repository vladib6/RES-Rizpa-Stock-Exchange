package com.Engine;

import com.Command.WaitingCommands.AllWaitingcommands;
import com.Transaction.Alltransactions;
import com.Transaction.Transaction;
import com.stock.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rizpa-stock-exchange-descriptor")
public class MainEngine {

    public MainEngine(){
        allWaitingcommands=new AllWaitingcommands();
        allStocks= new Allstocks();
        allTransactions= new Alltransactions();
        stockArray=new StockArray();
    }
   private AllWaitingcommands allWaitingcommands;
   private Allstocks allStocks;
   private Alltransactions allTransactions;
   private StockArray stockArray;

    @XmlElement(name="rse-stocks")
    public StockArray getStockArray() {
        return stockArray;
    }

    public void setStockArray(StockArray stockArray) {
        this.stockArray = stockArray;
    }

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
        if(allStocks.getAllStocks().containsKey(stock.getSymbol())){
            throw new Myexception("You can't create two stocks with the same Symbol !");
        }
        if(allStocks.isCompanyNameExist(stock.getCompanyName())){
            throw new Myexception("You can't create two stocks with the same Company name !");
        }else{
            allStocks.addStock(stock);
            allTransactions.addCellToHashMap(stock.getSymbol(),stock.getTransactionsList());
            allWaitingcommands.addCellToHashMap(stock.getSymbol(),stock.getBuyWaitinglist(),stock.getSellWaitinglist());
        }

    }


    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }



}
