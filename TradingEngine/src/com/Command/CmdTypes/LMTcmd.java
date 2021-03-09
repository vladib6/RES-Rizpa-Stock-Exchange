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
               Transaction transaction=DoTransaction(this,cmd,cmd.getPrice());
               if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                   sellWaitinglist.removeByObject(cmd);
               }
               return transaction;
           }
       }
        return null;
    }



    public Transaction FindBuycmd(BuyWaitinglist buyWaitinglist) {

        for(CommandType cmd: buyWaitinglist.getBuywaitinglist()){
            if(super.price<=cmd.getPrice()){
                Transaction transaction=DoTransaction(cmd,this,cmd.getPrice());
                if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                    buyWaitinglist.removeByObject(cmd);
                }
                return transaction;
            }
        }
        return null;
    }

    public Transaction DoTransaction(CommandType Buy,CommandType Sell,int price){// create and return transaction and update commands details.
        super.stock.setCurrentPrice(price);//Set new stock price
        if(Buy.getNumOfStocks()>=Sell.getNumOfStocks()){
            int numOfRelevantStocks=Sell.getNumOfStocks();
            Buy.setNumOfStocks(Buy.getNumOfStocks()-Sell.getNumOfStocks());
            Sell.setNumOfStocks(0);
            return new Transaction(price,super.stock,numOfRelevantStocks,price*numOfRelevantStocks,super.direction);
        }else{
            int numOfRelevantStocks=Buy.getNumOfStocks();
            Sell.setNumOfStocks(Sell.getNumOfStocks()-Buy.getNumOfStocks());
            Buy.setNumOfStocks(0);
            return new Transaction(price,super.stock,numOfRelevantStocks,price*numOfRelevantStocks,super.direction);
        }
    }

    @Override
    public int Execute() {
        int numOfTransactions=0;
        Transaction newTransaction;
        do {
            newTransaction= Findcmd();//try to find first matching opposite command and do transaction
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

    public Transaction Findcmd(){
        if(super.direction==Direction.SELL) {
            return FindBuycmd(super.stock.getBuyWaitinglist());
        }   else{
            return FindSellcmd(super.stock.getSellWaitinglist());
        }
    }
}