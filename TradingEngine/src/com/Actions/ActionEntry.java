package com.Actions;

import com.Command.CmdTypes.Direction;

public class ActionEntry {

    public ActionEntry(ActionsInterface action, Direction direction){
        this.type= action.getType();
        this.date= action.getDate();
        this.actionSum=direction.equals(Direction.BUY)? action.getTurnover()*-1 : action.getTurnover();
    }
    private  String type;
    private String date;
    private int actionSum;
    private  int oldSum;
    private  int newSum;

    public int getSum() { return actionSum; }

    public int getOldSum() { return oldSum; }

    public int getNewSum() { return newSum; }

    public void setOldSum(int oldSum) { this.oldSum = oldSum; }

    public void setNewSum(int newSum) { this.newSum = newSum; }
}
