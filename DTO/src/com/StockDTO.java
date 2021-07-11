package com;

import java.util.LinkedList;
import java.util.List;

public class StockDTO {//Data Transfer Object


    public StockDTO(String symbol, String companyName, int currentPrice, int transactionTurnover) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.TransactionTurnover = transactionTurnover;

    }

    private final String symbol;
    private final String companyName;
    private final int currentPrice;
    private final int TransactionTurnover;


    public String getSymbol() { return symbol; }

    public String getCompanyName() { return companyName; }

    public int getCurrentPrice() {return currentPrice; }

    public int getTransactionTurnover() { return TransactionTurnover; }

}
