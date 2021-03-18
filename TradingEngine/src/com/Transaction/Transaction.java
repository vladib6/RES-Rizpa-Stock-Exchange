package com.Transaction;

import com.Command.CmdTypes.Direction;
import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction {
    public Transaction(int price,Stock stock, int numOfStock, int turnover,Direction direction ){
        this.price=price;
        this.numOfStock=numOfStock;
        this.turnover=turnover;
        this.direction=direction;
        this.stock=stock;
        this.date=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
    }
    private String date;
    private int price;
    private int numOfStock;
    private int turnover;
    private Direction direction;
    private Stock stock;

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public int getNumOfStock() {
        return numOfStock;
    }

    public int getTurnover() {
        return turnover;
    }

    public Direction getDirection() {
        return direction;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public String toString(){
        return date+"   "+ "Number of stocks: "+numOfStock+"   "+ "Price : "+ price+"   "+"Turnover : "+ turnover+"$";
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
}
