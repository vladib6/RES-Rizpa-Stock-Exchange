package com.Command.WaitingCommands;

import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandType;
import com.Command.CmdTypes.Sellcmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SellWaitinglist extends CommandWaitinglist{
    public SellWaitinglist(){
        sellwaitinglist= new ArrayList<CommandType>();
    }

    class sortby implements Comparator<CommandType>{

        @Override
        public int compare(CommandType com1, CommandType com2) {
            if(com1.getPrice()!=com2.getPrice()){//compare by limit price
                return (com2.getPrice()-com1.getPrice());
            }else{//compare by date;
                return (com2.getId()-com1.getId());
            }
        }
    }
    private ArrayList<CommandType> sellwaitinglist;

    public ArrayList<CommandType> getSellwaitinglist(){return sellwaitinglist;}
    public void addCmdToList(CommandType command){
        sellwaitinglist.add(command);
        SortArrayList();
    }

    public void removeByObject(CommandType command){
        sellwaitinglist.remove(command);
    }
    public boolean isEmpyt(){
        return sellwaitinglist.isEmpty();
    }

    public void SortArrayList(){
        Collections.sort(sellwaitinglist,new SellWaitinglist.sortby());
    }


}
