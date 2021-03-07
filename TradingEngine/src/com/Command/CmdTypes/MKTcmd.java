package com.Command.CmdTypes;

import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.Transaction;
import com.stock.Stock;

public class MKTcmd extends CommandType {
    public MKTcmd(Direction direction, Stock stock, int numOfStocks) {
        super(direction, numOfStocks, stock,stock.getCurrentPrice());

    }


    @Override
    public Transaction FindSellcmd(SellWaitinglist sellWaitinglist) {
        return null;
    }

    @Override
    public Transaction FindBuycmd(BuyWaitinglist buyWaitinglist) {
        return null;
    }
}

