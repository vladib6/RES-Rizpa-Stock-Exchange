package com.stock;

import java.util.LinkedList;

import com.Command.CmdTypes.CommandType;
import com.Command.CmdTypes.Direction;
import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="rse-stock")
public class Stock {
    public Stock(){
        sellWaitinglist=new SellWaitinglist();
        buyWaitinglist=new BuyWaitinglist();
        transactionsList= new LinkedList<Transaction>();
    }
    public Stock(String symbol, String companyName, int price) {
        this.symbol = symbol.toUpperCase();
        this.companyName = companyName;
        this.currentPrice = price;
        sellWaitinglist=new SellWaitinglist();
        buyWaitinglist=new BuyWaitinglist();
        transactionsList= new LinkedList<Transaction>();
    }

    private  String symbol;
    private String companyName;
    private int currentPrice;
    private int TransactionTurnover;
    private  LinkedList<Transaction> transactionsList;
    private SellWaitinglist sellWaitinglist;
    private BuyWaitinglist buyWaitinglist;

    //Getters
    @XmlElement(name="rse-symbol")
    public String getSymbol() {
        return symbol;
    }

    @XmlElement (name="rse-company-name")
    public String getCompanyName() {
        return companyName;
    }

    @XmlElement(name="rse-price")
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
        return  "-->  Stock Data : "+symbol+ " -- "+ companyName+ "  Current Price: "+currentPrice +"  Number Of Transactions : "+transactionsList.size() + "  Turnover: "+TransactionTurnover;

    }


}

