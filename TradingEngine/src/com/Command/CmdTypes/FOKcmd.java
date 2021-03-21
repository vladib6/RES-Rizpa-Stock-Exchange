package com.Command.CmdTypes;


import com.User.Userinterface;
import com.stock.Stock;

public class FOKcmd extends CommandType {

    public FOKcmd(Userinterface user, Direction direction, String stockName, int numOfStocks) {

        super(user,direction, numOfStocks, stockName, 0);
    }


    @Override
    public int Execute(Stock stock) {
        return 0;
    }
}
