package com;

import com.Command.CmdTypes.Direction;

public class TransactionDTO {//Data Transfer Object

    public TransactionDTO(String date,int price, int numOfStock, int turnover, Direction direction , String buyer, String seller){
        this.price=price;
        this.numOfStock=numOfStock;
        this.turnover=turnover;
        this.direction=direction;
        this.date= date;
        this.buyer=buyer;
        this.seller=seller;
    }
    private String date;
    private int price;
    private int numOfStock;
    private int turnover;
    private Direction direction;
    String buyer;
    String seller;

    @Override
    public String toString(){
        return date+"    Number of stocks: "+numOfStock+"   "+ "Price : "+ price+"   "+"Turnover : "+ turnover+"$";
    }
}
