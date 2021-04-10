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

    private final String initiativeUser;
    private final String stockSymbol;
    private final Direction direction;
    private final String time;
    private final int numOfStocks;
    private final int price;

    public String getInitiativeUser() { return initiativeUser; }

    public String getStockSymbol() { return stockSymbol; }

    public Direction getDirection() { return direction; }

    public String getTime() { return time; }

    public int getNumOfStocks() { return numOfStocks; }

    public int getPrice() { return price; }

    @Override
    public String toString(){
        return time+"    "+ stockSymbol+"   "+direction+ "   Stocks : "+numOfStocks+"   "+"Price : "+price +"   Turnover :"+ numOfStocks*price+"$";
    }
}
