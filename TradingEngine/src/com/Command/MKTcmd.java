package com.Command;

import com.stock.Stock;

public class MKTcmd extends CommandType {
    public MKTcmd(Direction direction, Stock stock, int numOfStocks) {
        super(direction, numOfStocks, stock,stock.getCurrentPrice());

    }


    @Override
    public void EXE() {
        System.out.println("MKTcmd");
    }
}

