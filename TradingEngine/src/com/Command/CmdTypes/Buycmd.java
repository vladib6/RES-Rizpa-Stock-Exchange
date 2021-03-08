package com.Command.CmdTypes;

public class Buycmd extends Command {
    public Buycmd(CommandType command){
        this.command=command;
    }

   private CommandType command;


    public CommandType getCommand() { return command; }

    public void Execue(){
        command.Execute();
    }
}
