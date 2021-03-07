package com.Command;

import com.stock.Stock;

import java.security.InvalidParameterException;

public class CommandFactory {


    public static Command Createcmd(Direction direction, Type type, Stock stock, int numOfstocks, int limitprice){//the argument limitprice only use when create LMT command in others command will be sent as current price of stock
        Command res=null;
        switch (direction){
            case SELL://create Sell Command
                switch (type){
                    case LMT:
                        res=new Sellcmd(new LMTcmd(direction,stock, numOfstocks,limitprice));
                        break;
                    case MKT:
                        res=new Sellcmd(new MKTcmd(direction, stock,numOfstocks));
                        break;

                    case IOC:
                        res=new Sellcmd(new IOCcmd(direction,stock ,numOfstocks));
                        break;

                    case FOK:
                        res=new Sellcmd(new FOKcmd(direction,stock, numOfstocks));
                        break;

                    default:
                        throw new InvalidParameterException();
                }
                break;
            case BUY://create Buy command
                switch (type){
                    case LMT:
                        res=new Buycmd(new LMTcmd(direction,stock,numOfstocks,limitprice));
                        break;
                    case MKT:
                        res=new Buycmd(new MKTcmd(direction, stock,numOfstocks));
                        break;

                    case IOC:
                        res=new Buycmd(new IOCcmd(direction,stock ,numOfstocks));
                        break;

                    case FOK:
                        res=new Buycmd(new FOKcmd(direction,stock ,numOfstocks));
                        break;

                    default:
                        throw new InvalidParameterException();
                }
                break;

            default:
                throw new InvalidParameterException();

        }
        return res;
    }

}
