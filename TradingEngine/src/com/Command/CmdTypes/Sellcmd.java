package com.Command.CmdTypes;

public class Sellcmd extends Command {
    public Sellcmd(CommandType command){
        this.command=command;
    }

    private CommandType command;

    public CommandType getCommand() {
        return command;
    }

    public void Execue(){
        command.Execute();
    }

}
