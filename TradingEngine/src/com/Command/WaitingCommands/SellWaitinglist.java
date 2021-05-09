package com.Command.WaitingCommands;

import com.Command.CmdTypes.CommandType;
import com.CommandDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SellWaitinglist extends CommandWaitinglist{
    public SellWaitinglist(){
        sellwaitinglist= new ArrayList<>();
    }

    class sortby implements Comparator<CommandType>{

        @Override
        public int compare(CommandType com1, CommandType com2) {
            if(com1.getPrice()!=com2.getPrice()){//compare by limit price
                return (com1.getPrice()-com2.getPrice());
            }else{//compare by date;
                return (com1.getId()-com2.getId());
            }
        }
    }
    private List<CommandType> sellwaitinglist;

    public List<CommandType> getSellwaitinglist(){return sellwaitinglist;}
    public CommandType getFirst(){
       return sellwaitinglist.get(0);
    }

    public void addCmdToList(CommandType command){
        sellwaitinglist.add(command);
        SortArrayList();
    }

    public void removeByObject(CommandType command){
        sellwaitinglist.remove(command);
    }

    public void SortArrayList(){
        Collections.sort(sellwaitinglist,new SellWaitinglist.sortby());
    }

    public List<CommandDTO> createList(){
        List<CommandDTO> res=new ArrayList<>();
        for(CommandType cmd:sellwaitinglist){
            res.add(new CommandDTO(cmd.getInitiativeUser().getUsername(), cmd.getStockSymbol(),cmd.getDirection(),cmd.getTime(),cmd.getNumOfStocks(),cmd.getPrice(),cmd.getClass().getSimpleName()));
        }
        return res;
    }
}
