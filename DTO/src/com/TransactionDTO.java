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
    private final String date;
    private final int price;
    private final int numOfStock;
    private final int turnover;
    private final Direction direction;
    private final String buyer;
    private final String seller;

    public String getDate() { return date; }

    public int getPrice() { return price; }

    public int getNumOfStock() { return numOfStock; }

    public int getTurnover() { return turnover; }

    public Direction getDirection() { return direction; }

    public String getBuyer() { return buyer; }

    public String getSeller() { return seller; }

    @Override
    public String toString(){
        return date+"    Number of stocks: "+numOfStock+"   "+ "Price : "+ price+"   "+"Turnover : "+ turnover+"$";
    }
}
