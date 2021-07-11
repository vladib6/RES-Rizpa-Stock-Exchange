package com.Command.CmdTypes;

import com.User.Traderinterface;

import java.security.InvalidParameterException;

public class CommandFactory {

    public static Command Createcmd(Traderinterface user, Direction direction, Type type, String symbol, int numOfstocks, int limitprice){//the argument limitprice only use when create LMT command in others command will be sent as current price of stock
        Command res=null;
        switch (direction){
            case SELL://create Sell Command
                switch (type){
                    case LMT:
                        res=new Sellcmd(new LMTcmd(user,direction,symbol, numOfstocks,limitprice));
                        break;
                    case MKT:
                        res=new Sellcmd(new MKTcmd(user,direction, symbol,numOfstocks));
                        break;

                    case IOC:
                        res=new Sellcmd(new IOCcmd(user,direction,symbol ,numOfstocks));
                        break;

                    case FOK:
                        res=new Sellcmd(new FOKcmd(user,direction,symbol, numOfstocks));
                        break;

                    default:
                        throw new InvalidParameterException("The type of command are  Wrong");
                }
                break;
            case BUY://create Buy command
                switch (type){
                    case LMT:
                        res=new Buycmd(new LMTcmd(user,direction,symbol,numOfstocks,limitprice));
                        break;
                    case MKT:
                        res=new Buycmd(new MKTcmd(user,direction, symbol,numOfstocks));
                        break;

                    case IOC:
                        res=new Buycmd(new IOCcmd(user,direction,symbol ,numOfstocks));
                        break;

                    case FOK:
                        res=new Buycmd(new FOKcmd(user,direction,symbol ,numOfstocks));
                        break;

                    default:
                        throw new InvalidParameterException("The type of command are  Wrong");
                }
                break;

            default:
                throw new InvalidParameterException();

        }
        return res;
    }

}
