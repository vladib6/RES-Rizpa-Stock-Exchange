package com.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SellWaitinglist extends CommandWaitinglist{
    public SellWaitinglist(){
        sellwaitinglist= new ArrayList<Command>();
    }

    class sortby implements Comparator<Command>{

        @Override
        public int compare(Command com1, Command com2) {
            if(((Sellcmd)com1).getlimitprice()!=((Sellcmd)com2).getlimitprice()){//compare by limit price
                return ((Sellcmd)com2).getlimitprice()-((Sellcmd)com1).getlimitprice();
            }else{//compare by date;
                return ((Sellcmd)com2).getid()-((Sellcmd)com1).getid();
            }
        }
    }
    private ArrayList<Command> sellwaitinglist;

    public ArrayList<Command> getSellwaitinglist(){return sellwaitinglist;}
    public void addCmdToList(Command command){

        sellwaitinglist.add(command);
        Collections.sort(sellwaitinglist,new sortby());
    }

    public void removeByObject(Command command){
        sellwaitinglist.remove(command);
    }
    public boolean isEmpyt(){
        return sellwaitinglist.isEmpty();
    }


}
