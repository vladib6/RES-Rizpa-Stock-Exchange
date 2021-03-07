package com.Command;

import com.stock.Stock;

public class LMTcmd extends CommandType {

    public LMTcmd(Direction direction, Stock stock, int numOfStocks, int limitprice) {
        super(direction, numOfStocks,stock,limitprice);
    }


    @Override
    public void EXE() {
        System.out.printf("LKTCMD");
    }
}