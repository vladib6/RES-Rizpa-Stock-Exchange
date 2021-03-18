package com.Command.CmdTypes;

import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.Transaction;
import com.stock.Stock;

public class MKTcmd extends CommandType {
    public MKTcmd(Direction direction, Stock stock, int numOfStocks) {
        super(direction, numOfStocks, stock,stock.getCurrentPrice());

    }


    @Override
    public int Execute() {//return the number if transaction that made
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


    public Transaction FindSellcmd(SellWaitinglist sellWaitinglist) {//find the matching sell command to buy command that execute
                if(sellWaitinglist.getSellwaitinglist().size()==0){//if the waiting command list is empty return null
                    return null;
                }else {
                    Transaction transaction=DoTransaction(this,sellWaitinglist.getFirst(),sellWaitinglist.getFirst().price);
                    this.price=transaction.getPrice();//update the command price in case we pass the command to waiting list
                    if(sellWaitinglist.getFirst().numOfStocks==0){ //if numofstock is 0 so remove the cmd from waiting list
                        sellWaitinglist.getSellwaitinglist().remove(0);
                    }
                    return transaction;
                }
    }

    public Transaction FindBuycmd(BuyWaitinglist buyWaitinglist) {
        if(buyWaitinglist.getBuywaitinglist().size()==0){//if the waiting command list is empty return null
            return null;
        }else {
            Transaction transaction=DoTransaction(buyWaitinglist.getFirst(),this,buyWaitinglist.getFirst().price);
            this.price=transaction.getPrice();
            if(buyWaitinglist.getFirst().numOfStocks==0){ //if numofstock is 0 so remove the cmd from waiting list
                buyWaitinglist.getBuywaitinglist().remove(0);
            }
            return transaction;
        }
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
    public Transaction Findcmd(){
        if(super.direction==Direction.SELL) {
            return FindBuycmd(super.stock.getBuyWaitinglist());
        }   else{
            return FindSellcmd(super.stock.getSellWaitinglist());
        }
    }

    }