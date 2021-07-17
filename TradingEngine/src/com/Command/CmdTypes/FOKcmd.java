package com.Command.CmdTypes;


import com.Actions.Transaction;
import com.AlertDTO;
import com.User.Traderinterface;
import com.stock.Stock;

import java.util.ArrayList;
import java.util.List;

public class FOKcmd extends CommandType {

    public FOKcmd(Traderinterface user, Direction direction, String stockName, int numOfStocks,int limitprice) {
        super(user,direction, numOfStocks, stockName, limitprice);
    }

    @Override
    public int Execute(Stock stock) {
        synchronized (stock) {
            if (checkIfPossible(stock)) {
                int numOfTransactions = 0;
                Transaction newTransaction;
                do {
                    newTransaction = Findcmd(stock);//try to find first matching opposite command and do transaction
                    if (newTransaction != null) {
                        stock.addTransaction(newTransaction);
                        numOfTransactions++;
                        stock.setTransactionTurnover(newTransaction.getTurnover());
                    }
                }
                while (newTransaction != null && numOfStocks != 0);//while has stocks in command and success do transaction(not return null)
                return numOfTransactions;
            }
            return 0;//the command not execute
        }
    }

    public Transaction Findcmd(Stock stock){
        if(super.direction==Direction.SELL) {
            return FindBuycmd(stock);
        }   else{
            return FindSellcmd(stock);
        }
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
            for (CommandType cmd : stock.getBuyWaitinglist().getBuywaitinglist()) {
                if (super.price <= cmd.getPrice()) {
                    Transaction transaction = DoTransaction(cmd, this, cmd.getPrice(), stock);
                    if (cmd.getNumOfStocks() == 0) { //if numofstock is 0 so remove the cmd from waiting list
                        stock.getBuyWaitinglist().removeByObject(cmd);
                    }
                    return transaction;
                }
            }
            return null;
    }

    public boolean checkIfPossible(Stock stock){
        int countStocks=numOfStocks;
            if(super.direction==Direction.SELL) {
                List<CommandType> list=stock.getBuyWaitinglist().getBuywaitinglist();
                for(CommandType cmd:list) {
                    if (super.price >= cmd.getPrice()) {
                            if(countStocks>=cmd.getNumOfStocks()){
                                countStocks-=cmd.getNumOfStocks();
                            }else{
                                //countStocks=0
                                return true;
                            }
                    }
                }
                if(countStocks<=0){
                    return true;
                }else{
                    return false;
                }
            }else{
                List<CommandType> list=stock.getSellWaitinglist().getSellwaitinglist();
                for(CommandType cmd:list) {
                    if (super.price <= cmd.getPrice()) {
                        if (countStocks <= cmd.getNumOfStocks()) {
                            //countStocks=0;
                            return true;
                        } else {
                            countStocks -= cmd.getNumOfStocks();
                        }
                    }
                }
                    if(countStocks<=0){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }

