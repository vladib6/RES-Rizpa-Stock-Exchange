package com.Command.CmdTypes;

import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.Transaction;
import com.stock.Stock;

public class LMTcmd extends CommandType {

    public LMTcmd(Direction direction, Stock stock, int numOfStocks, int limitprice) {
        super(direction, numOfStocks,stock,limitprice);
    }

    @Override
    public Transaction FindSellcmd(SellWaitinglist sellWaitinglist) {
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

    @Override
    public Transaction FindBuycmd(BuyWaitinglist buyWaitinglist) {
        return null;
    }

    public Transaction DoTransaction(CommandType Buy,CommandType Sell){
        Buy.getStock().setCurrentPrice(Buy.getPrice());//Set new stock price

        if(Buy.getNumOfStocks()>=Sell.getNumOfStocks()){
            Buy.setNumOfStocks(Buy.getNumOfStocks()-Sell.getNumOfStocks());
            Sell.setNumOfStocks(0);
            return new Transaction(Buy.getPrice(),this.getStock(),Sell.getNumOfStocks(),Buy.getPrice()*Sell.getNumOfStocks(),this.getDirection());

        }else{
            Sell.setNumOfStocks(Sell.getNumOfStocks()-Buy.getNumOfStocks());
            Buy.setNumOfStocks(0);
            return new Transaction(Buy.getPrice(),this.getStock(),Buy.getNumOfStocks(),Buy.getPrice()*Buy.getNumOfStocks(),this.getDirection());
        }
    }
}