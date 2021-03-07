package com.Transaction;

import com.Command.Direction;
import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    Transaction(int price,Stock stock, int numOfStock, int turnover,Direction direction ){
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

    public void Show(){
        System.out.println(date+"   "+ "Number of stocks: "+numOfStock+"   "+ "Price : "+ price+"   "+"Turnover : "+ turnover );
    }
}
