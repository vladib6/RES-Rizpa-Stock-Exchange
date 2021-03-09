package com.Command.CmdTypes;


import com.stock.Stock;

public class IOCcmd extends CommandType {
    public IOCcmd(Direction direction, Stock stock, int numOfStocks) {

        super(direction, numOfStocks,stock,stock.getCurrentPrice());

    }


    @Override
    public int Execute() {
        return 0;
    }
}
