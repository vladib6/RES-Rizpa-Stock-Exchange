package com.Actions;

import com.Command.CmdTypes.Direction;
import com.TransactionDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction implements ActionsInterface {
    public Transaction(int price,String stockName, int numOfStock, int turnover,Direction direction ,String buyer,String seller){
        this.price=price;
        this.numOfStock=numOfStock;
        this.turnover=turnover;
        this.direction=direction;
        this.stockName=stockName;
        this.date=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
        this.buyer=buyer;
        this.seller=seller;
    }
    private final String date;
    private final int price;
    private final int numOfStock;
    private final int turnover;
    private final Direction direction;
    private final String stockName;
    private final String buyer;
    private final String seller;

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getType() {
        String type=direction.equals(Direction.BUY)?"Buy":"Sell";
        return "Transcation -"+type;
    }

    public int getPrice() {
        return price;
    }

    public int getNumOfStock() {
        return numOfStock;
    }
    @Override
    public int getTurnover() {
        return turnover;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getStockName() {
        return stockName;
    }

    public String getBuyer() { return buyer; }

    public String getSeller() { return seller; }

    @Override
    public String toString(){
        return date+"   Seller: "+seller+"   Buyer: "+buyer+ "   Number of stocks: "+numOfStock+"   "+ "Price : "+ price+"   "+"Turnover : "+ turnover+"$";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return price == that.price && numOfStock == that.numOfStock && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, price, numOfStock);
    }


    public TransactionDTO createDTO(){
        return new TransactionDTO(date,price,numOfStock,turnover,direction,buyer,seller);
    }
}


