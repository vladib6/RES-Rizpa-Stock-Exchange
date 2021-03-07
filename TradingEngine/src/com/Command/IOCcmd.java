package com.Command;

import com.stock.Stock;

public class IOCcmd extends CommandType {
    public IOCcmd(Direction direction, Stock stock, int numOfStocks) {

        super(direction, numOfStocks,stock,stock.getCurrentPrice());

    }

    @Override
    public void EXE() {
        System.out.println("IOCcmd");
    }
}
