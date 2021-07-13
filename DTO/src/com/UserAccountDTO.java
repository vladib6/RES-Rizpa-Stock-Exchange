package com;

import com.Actions.ActionEntry;

import java.util.LinkedList;

public class UserAccountDTO {

    public UserAccountDTO(int accountBalance, LinkedList<ActionEntry> actionsHistory){
        this.accounteBalance=accountBalance;
        this.actionsHistory=actionsHistory;
    }

    private int accounteBalance;
    private LinkedList<ActionEntry> actionsHistory;

    public int getAccounteBalance() { return accounteBalance; }

    public LinkedList<ActionEntry> getActionsHistory() { return actionsHistory; }
}
