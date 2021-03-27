package com;

import com.Command.CmdTypes.Direction;

public class CommandDTO {//Data Transfer Object

    public CommandDTO(String initiativeUser, String stockSymbol, Direction direction, String time, int numOfStocks, int price) {
        this.initiativeUser = initiativeUser;
        this.stockSymbol = stockSymbol;
        this.direction = direction;
        this.time = time;
        this.numOfStocks = numOfStocks;
        this.price = price;
    }

    private String initiativeUser;
    private String stockSymbol;
    private Direction direction;
    private String time;
    private int numOfStocks;
    private int price;

    @Override
    public String toString(){
        return time+"    "+ stockSymbol+"   "+direction+ "   Stocks : "+numOfStocks+"   "+"Price : "+price +"   Turnover :"+ numOfStocks*price+"$";
    }
}
