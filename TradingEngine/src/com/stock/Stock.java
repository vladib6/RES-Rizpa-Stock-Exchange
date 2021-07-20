package com.stock;

import com.ChartData;
import com.Command.CmdTypes.CommandType;
import com.Command.CmdTypes.Direction;
import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.CommandDTO;
import com.StockDTO;
import com.Actions.Transaction;
import com.TransactionDTO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlRootElement (name="rse-stock")
public class Stock {
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
    private final LinkedList<Transaction> transactionsList;
    private final SellWaitinglist sellWaitinglist;
    private final BuyWaitinglist buyWaitinglist;

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

    public int getNumOfTransactions(){ return transactionsList.size(); }

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
        return  "-->  Stock Data : "+symbol+ " -- "+ companyName+ "  Current Price: "+currentPrice +"  Number Of Transactions : "+transactionsList.size() + "  Turnover: "+TransactionTurnover+"$";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return symbol.equals(stock.symbol) && companyName.equals(stock.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName);
    }

    //Data Transfer Objects
    public LinkedList<TransactionDTO> createTransactionDTOlist(){
            LinkedList<TransactionDTO> res=new LinkedList<>();
            for(Transaction trs: transactionsList){
                res.add(new TransactionDTO(trs.getDate(),trs.getPrice(),trs.getNumOfStock()));
            }

            return res;
    }
    public StockDTO createStockDto(){ return new StockDTO(symbol,companyName,currentPrice,TransactionTurnover); }
    public List<CommandDTO> getSellCommandsDto(){return sellWaitinglist.createList();}
    public List<CommandDTO> getBuyCommandsDto(){return buyWaitinglist.createList();}

    public LinkedList<ChartData> getStockChart(){
        LinkedList<ChartData> res=new LinkedList<>();
        for(Transaction transaction:transactionsList){
            res.addFirst(new ChartData(transaction.getDate(), transaction.getPrice()));
        }
        return  res;
    }

}

