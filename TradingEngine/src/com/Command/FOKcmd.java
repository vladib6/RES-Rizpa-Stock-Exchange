package com.Command;

import com.stock.Stock;

public class FOKcmd extends CommandType {

    public FOKcmd(Direction direction, Stock stock, int numOfStocks) {

        super(direction, numOfStocks,stock,stock.getCurrentPrice());
    }


    @Override
    public void EXE() {
        System.out.println("FOKcmd");
    }
}
