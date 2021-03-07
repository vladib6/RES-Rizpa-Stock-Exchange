package com.Command;

public class Sellcmd extends Command {
    public Sellcmd(CommandType command){
        super();
        this.command=command;
    }

    private CommandType command;

    public CommandType getCommand() {
        return command;
    }

    public int getlimitprice(){
        return command.getPrice();
    }

    public int getid(){
        return command.getId();
    }

}
