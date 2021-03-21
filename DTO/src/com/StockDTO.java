package com;

import java.util.LinkedList;
import java.util.List;

public class StockDTO {//Data Transfer Object


    public StockDTO(String symbol, String companyName, int currentPrice, int transactionTurnover, int numOfTransactions,List<CommandDTO> sellWaiting,List<CommandDTO> buyWaiting,LinkedList<TransactionDTO> transactionDTOS) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        TransactionTurnover = transactionTurnover;
        this.numOfTransactions = numOfTransactions;
        this.buyWaiting=buyWaiting;
        this.sellWaiting=sellWaiting;
        this.transactionDTOS=transactionDTOS;
    }

    private final String symbol;
    private final String companyName;
    private final int currentPrice;
    private final int TransactionTurnover;
    private final int numOfTransactions;
    private List<CommandDTO> sellWaiting;
    private List<CommandDTO> buyWaiting;
    private LinkedList<TransactionDTO> transactionDTOS;
    @Override
    public String toString(){
        return  "-->  Stock Data : "+symbol+ " -- "+ companyName+ "  Current Price: "+currentPrice +"  Number Of Transactions : "+numOfTransactions + "  Turnover: "+TransactionTurnover+" $";

    }

    public List<CommandDTO> getSellWaiting() { return sellWaiting; }

    public List<CommandDTO> getBuyWaiting() { return buyWaiting; }

    public LinkedList<TransactionDTO> getTransactionDTOS() { return transactionDTOS; }

    public String getSymbol() { return symbol; }

    public String getCompanyName() { return companyName; }
}
