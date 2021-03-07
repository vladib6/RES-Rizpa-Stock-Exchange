package com.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BuyWaitinglist extends CommandWaitinglist{
    public BuyWaitinglist(){
    buywaitinglist=new ArrayList<Command>();
    }


    class sortby implements Comparator<Command>{

        @Override
        public int compare(Command com1, Command com2) {
            if(((Buycmd)com1).getlimitprice()!=((Buycmd)com2).getlimitprice()){//compare by limit price
                return ((Buycmd)com1).getlimitprice()-((Buycmd)com2).getlimitprice();
            }else{//compare by date;
                return ((Buycmd)com2).getid()-((Buycmd)com1).getid();
            }
        }
    }
    private ArrayList<Command> buywaitinglist;

    public ArrayList<Command> getBuylwaitinglist(){return buywaitinglist;}

    public void addCmdToList(Command command){

        buywaitinglist.add(command);
        Collections.sort(buywaitinglist,new sortby());
    }

    public boolean isEmpyt(){
        return buywaitinglist.isEmpty();
    }



}
