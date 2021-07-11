package com.Command.CmdTypes;


import com.User.Traderinterface;
import com.stock.Stock;

public class IOCcmd extends CommandType {
    public IOCcmd(Traderinterface user, Direction direction, String stockName, int numOfStocks) {

        super(user,direction, numOfStocks,stockName,0);

    }


    @Override
    public int Execute(Stock stock) {
        return 0;
    }
}
