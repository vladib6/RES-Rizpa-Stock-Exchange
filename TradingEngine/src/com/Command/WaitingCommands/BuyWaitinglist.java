package com.Command.WaitingCommands;

import com.Command.CmdTypes.CommandType;
import com.CommandDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BuyWaitinglist extends CommandWaitinglist{
    public BuyWaitinglist(){
    buywaitinglist=new ArrayList<>();
    }


    class sortby implements Comparator<CommandType>{

        @Override
        public int compare(CommandType com1, CommandType com2) {
            if(com1.getPrice()!=com2.getPrice()){//compare by limit price
                return (com2.getPrice()-com1.getPrice());
            }else{//compare by date=compare by id;
                return (com1.getId()-com2.getId());
            }
        }
    }
    private List<CommandType> buywaitinglist;

    public List<CommandType> getBuywaitinglist(){return buywaitinglist;}

    public CommandType getFirst(){
        return buywaitinglist.get(0);
    }

    public void addCmdToList(CommandType command){
        buywaitinglist.add(command);
        SortArrayList();
    }

    public void removeByObject(CommandType command){
        buywaitinglist.remove(command);
    }

    public void removeFirst(){
        buywaitinglist.remove(0);
    }
    public boolean isEmpyt(){
        return buywaitinglist.isEmpty();
    }

    public void SortArrayList(){
        Collections.sort(buywaitinglist,new sortby());
    }

    public List<CommandDTO> createList(){
        List<CommandDTO> res=new ArrayList<>();
        for(CommandType cmd:buywaitinglist){
            res.add(new CommandDTO(cmd.getInitiativeUser().getUsername(),cmd.getStockSymbol(),cmd.getDirection(),cmd.getTime(),cmd.getNumOfStocks(),cmd.getPrice(),cmd.getClass().getSimpleName()));
        }
        return res;

    }
}
