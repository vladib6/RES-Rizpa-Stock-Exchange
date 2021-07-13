package com;

import com.Command.CmdTypes.Direction;

public class TransactionDTO {//Data Transfer Object

    public TransactionDTO(String date,int price, int numOfStock){
        this.price=price;
        this.numOfStock=numOfStock;
        this.date= date;

    }
    private final String date;
    private final int price;
    private final int numOfStock;


    public String getDate() { return date; }

    public int getPrice() { return price; }

    public int getNumOfStock() { return numOfStock; }


}
