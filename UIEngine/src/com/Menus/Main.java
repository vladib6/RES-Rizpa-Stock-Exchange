package com.Menus;

import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.Direction;
import com.Command.CmdTypes.MKTcmd;
import com.Command.CmdTypes.Sellcmd;
import com.stock.Stock;


public class Main {


    public static void main(String[] args) {
//        MainMenu main=new MainMenu();
//        main.Run();

        Command cmd= new Sellcmd(new MKTcmd(Direction.SELL,new Stock("GOO","google",100,25),5));

        System.out.println(cmd.getCommand().getClass().getSimpleName());
    }
}
