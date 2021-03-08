package com.stock;

import java.util.LinkedList;

import com.Command.CmdTypes.CommandType;
import com.Command.CmdTypes.Direction;
import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.*;
//After create stock we must to add the transactionsList to AllTransaction Hashmap.
public class Stock {

    public Stock(String symbol, String companyName, int numOfStocks, int currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.numOfStocks = numOfStocks;
        this.currentPrice = currentPrice;
        sellWaitinglist=new SellWaitinglist();
        buyWaitinglist=new BuyWaitinglist();
        transactionsList= new LinkedList<Transaction>();
        TransactionTurnover=0;
        id++;
    }
    private static int id=0;
    private  String symbol;
    private String companyName;
    private int numOfStocks;
    private int currentPrice;
    private int TransactionTurnover;
    private  LinkedList<Transaction> transactionsList;
    private SellWaitinglist sellWaitinglist;
    private BuyWaitinglist buyWaitinglist;

    //Getters
    public String getSymbol() {
        return symbol;
    }

    public int getId(){ return id; }
    public String getCompanyName() {
        return companyName;
    }

    public int getNumOfStocks() {
        return numOfStocks;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public int getTransactionTurnover() {
        return TransactionTurnover;
    }

    public LinkedList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public SellWaitinglist getSellWaitinglist() { return sellWaitinglist; }

    public BuyWaitinglist getBuyWaitinglist() {
        return buyWaitinglist;
    }

    //Setters
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNumOfStocks(int numOfStocks) {
        this.numOfStocks = numOfStocks;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setTransactionTurnover(int transactionTurnover) {
        TransactionTurnover += transactionTurnover;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTransactionsList(LinkedList<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public void addTransaction(Transaction transaction){//add to head of list
        transactionsList.addFirst(transaction);

    }

    public void addWaitingCommand(CommandType cmd){
        if(cmd.getDirection()== Direction.BUY){
            buyWaitinglist.addCmdToList(cmd);
        }else{
            sellWaitinglist.addCmdToList(cmd);
        }
    }

    @Override
    public String toString(){
        return  id+"-  Stock Data : "+symbol+ " -- "+ companyName+ "  Current Price: "+currentPrice +"  Number Of Transactions : "+transactionsList.size() + "  Turnover: "+TransactionTurnover;

    }

}

