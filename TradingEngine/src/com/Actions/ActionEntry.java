package com.Actions;

public class ActionEntry {

    public ActionEntry(ActionsInterface action){
        this.action=action;
    }

    private final ActionsInterface action;
    private  int oldSum;
    private  int newSum;

    public ActionsInterface getAction() { return action; }

    public int getOldSum() { return oldSum; }

    public int getNewSum() { return newSum; }

    public void setOldSum(int oldSum) { this.oldSum = oldSum; }

    public void setNewSum(int newSum) { this.newSum = newSum; }
}
