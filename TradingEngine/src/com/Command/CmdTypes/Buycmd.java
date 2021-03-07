package com.Command.CmdTypes;

public class Buycmd extends Command {
    public Buycmd(CommandType command){
        this.command=command;
    }

   private CommandType command;


    public CommandType getCommand() { return command; }

    public int getlimitprice(){
       return command.getPrice();
    }

    public int getid(){
        return command.getId();
    }
}
