package com;

import com.Command.CmdTypes.Direction;

public class CommandDTO {//Data Transfer Object

    public CommandDTO(String initiativeUser, Direction direction, String time, int numOfStocks, int price,String type) {
        this.initiativeUser = initiativeUser;
        this.direction = direction;
        this.time = time;
        this.numOfStocks = numOfStocks;
        this.price = price;
        this.type=type.substring(0,3);
    }
    private final String type;
    private final String initiativeUser;
    private final Direction direction;
    private final String time;
    private final int numOfStocks;
    private final int price;

    public String getInitiativeUser() { return initiativeUser; }


    public Direction getDirection() { return direction; }

    public String getTime() { return time; }

    public int getNumOfStocks() { return numOfStocks; }

    public int getPrice() { return price; }

    public String getType() { return type; }

}
