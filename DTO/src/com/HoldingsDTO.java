package com;

public class HoldingsDTO {

    public HoldingsDTO(int quantity, String symbol, int stockPrice) {
        this.quantity = quantity;
        this.symbol = symbol;
        this.stockPrice = stockPrice;
    }
    int quantity;
    String symbol;
    int stockPrice;

    public int getQuantity() { return quantity; }

    public String getSymbol() { return symbol; }

    public int getStockPrice() { return stockPrice; }


    public String toString(){
        return "Symbol : "+symbol+"   Price :"+stockPrice+"   Quantity :"+quantity +"\n";
    }
}
