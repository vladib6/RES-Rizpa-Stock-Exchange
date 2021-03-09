package com.Command.WaitingCommands;

import com.Command.CmdTypes.Buycmd;
import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BuyWaitinglist extends CommandWaitinglist{
    public BuyWaitinglist(){
    buywaitinglist=new ArrayList<CommandType>();
    }


    class sortby implements Comparator<CommandType>{

        @Override
        public int compare(CommandType com1, CommandType com2) {
            if(com1.getPrice()!=com2.getPrice()){//compare by limit price
                return (com1.getPrice()-com2.getPrice());
            }else{//compare by date;
                return (com2.getId()-com1.getId());
            }
        }
    }
    private ArrayList<CommandType> buywaitinglist;

    public ArrayList<CommandType> getBuywaitinglist(){return buywaitinglist;}

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

}
