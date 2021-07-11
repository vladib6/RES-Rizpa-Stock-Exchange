package com.Command.CmdTypes;

import com.Actions.Transaction;
import com.User.Traderinterface;
import com.stock.Stock;

public class LMTcmd extends CommandType {

    public LMTcmd(Traderinterface user, Direction direction, String stockName, int numOfStocks, int limitprice) {
        super(user,direction, numOfStocks,stockName,limitprice);
    }


    public Transaction FindSellcmd(Stock stock) {//find the matching sell command to buy command that execute
       for(CommandType cmd: stock.getSellWaitinglist().getSellwaitinglist()){
           if(super.getPrice()>=cmd.getPrice()){
               Transaction transaction=DoTransaction(this,cmd,cmd.getPrice(),stock);
               if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                   stock.getSellWaitinglist().removeByObject(cmd);
               }
               return transaction;
           }
       }
        return null;
    }

    public Transaction FindBuycmd(Stock stock) {
        for(CommandType cmd: stock.getBuyWaitinglist().getBuywaitinglist()){
            if(super.price<=cmd.getPrice()){
                Transaction transaction=DoTransaction(cmd,this,cmd.getPrice(),stock);
                if(cmd.getNumOfStocks()==0){ //if numofstock is 0 so remove the cmd from waiting list
                    stock.getBuyWaitinglist().removeByObject(cmd);
                }
                return transaction;
            }
        }
        return null;
    }



    @Override
    public int Execute(Stock stock) {
        int numOfTransactions=0;
        Transaction newTransaction;
        do {
            newTransaction= Findcmd(stock);//try to find first matching opposite command and do transaction
            if(newTransaction!=null){
                stock.addTransaction(newTransaction);
                numOfTransactions++;
                stock.setTransactionTurnover(newTransaction.getTurnover());
            }
        }
        while(newTransaction!=null && numOfStocks!=0);//while has stocks in command and success do transaction(not return null)

        if(numOfStocks>0) {//if there is stocks in command so add the command to waiting list
            stock.addWaitingCommand(this);
        }
        return numOfTransactions;
    }

    public Transaction Findcmd(Stock stock){
        if(super.direction==Direction.SELL) {
            return FindBuycmd(stock);
        }   else{
            return FindSellcmd(stock);
        }
    }
}