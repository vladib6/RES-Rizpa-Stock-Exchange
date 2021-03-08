package com.Command.CmdTypes;

import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.Transaction;
import com.stock.Stock;

public class LMTcmd extends CommandType {

    public LMTcmd(Direction direction, Stock stock, int numOfStocks, int limitprice) {
        super(direction, numOfStocks,stock,limitprice);
    }


    public Transaction FindSellcmd(SellWaitinglist sellWaitinglist) {//find the matching sell command to buy command that execute
       for(CommandType cmd: sellWaitinglist.getSellwaitinglist()){
           if(super.getPrice()>=cmd.getPrice()){
               Transaction transaction=DoTransaction(this,cmd);
               if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                   sellWaitinglist.removeByObject(cmd);
               }
               return transaction;
           }
       }
        return null;
    }


    public Transaction FindBuycmd(BuyWaitinglist buyWaitinglist) {
        for(CommandType cmd: buyWaitinglist.getBuylwaitinglist()){
            if(super.getPrice()<=cmd.getPrice()){
                Transaction transaction=DoTransaction(cmd,this);
                if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                    buyWaitinglist.removeByObject(cmd);
                }
                return transaction;
            }
        }
        return null;
    }

    public Transaction DoTransaction(CommandType Buy,CommandType Sell){// create and return transaction and update commands details.
        Buy.getStock().setCurrentPrice(Buy.getPrice());//Set new stock price
//TODO: ask in which purchase rate the transaction execute buy or sell and decide where to update current price
        if(Buy.getNumOfStocks()>=Sell.getNumOfStocks()){
            int numOfRelevantStocks=Sell.getNumOfStocks();
            Buy.setNumOfStocks(Buy.getNumOfStocks()-Sell.getNumOfStocks());
            Sell.setNumOfStocks(0);
            return new Transaction(Buy.getPrice(),this.getStock(),numOfRelevantStocks,Buy.getPrice()*Sell.getNumOfStocks(),this.getDirection());
        }else{
            int numOfRelevantStocks=Buy.getNumOfStocks();
            Sell.setNumOfStocks(Sell.getNumOfStocks()-Buy.getNumOfStocks());
            Buy.setNumOfStocks(0);
            return new Transaction(Buy.getPrice(),this.getStock(),numOfRelevantStocks,Buy.getPrice()*Buy.getNumOfStocks(),this.getDirection());
        }
    }

    @Override
    public int SellExecute() {
        int numOfTransactions=0;
        Transaction newTransaction;
        do {
            newTransaction= FindBuycmd(stock.getBuyWaitinglist());//try to find first matching opposite command and do transaction
            if(newTransaction!=null){
                stock.addTransaction(newTransaction);
                numOfTransactions++;
                stock.setTransactionTurnover(newTransaction.getTurnover());
            }
        }
        while(newTransaction!=null && numOfStocks!=0);//while has stocks in command and success do transaction

        if(numOfStocks>0) {//if there is stocks in command so add the command to waiting list
            stock.addWaitingCommand(this);
        }

        return numOfTransactions;
    }

    @Override
    public int BuyExecute() {
        int numOfTransactions=0;
        Transaction newTransaction;
        do {
            newTransaction= FindSellcmd(stock.getSellWaitinglist());//try to find first matching opposite command and do transaction
            if(newTransaction!=null){
                stock.addTransaction(newTransaction);
                numOfTransactions++;
                stock.setTransactionTurnover(newTransaction.getTurnover());
            }
        }
        while(newTransaction!=null && numOfStocks!=0);//while has stocks in command and success do transaction

        if(numOfStocks>0) {//if there is stocks in command so add the command to waiting list
            stock.addWaitingCommand(this);
        }
        return numOfTransactions;
    }
}