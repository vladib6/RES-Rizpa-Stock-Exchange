package com;

import java.util.List;

public class UserDTO {//Data Transfer Object


    public UserDTO(int totalHoldings, String username, int accountBalance,int totalTransactions) {
        this.totalHoldings = totalHoldings;
        this.username = username;
        this.accountBalance=accountBalance;
        this.totalTransactions=totalTransactions;
    }

    private final int totalHoldings;
    private final String username;
    private final int accountBalance;
    private final int totalTransactions;
    public int getTotalHoldings() { return totalHoldings; }

    public String getUsername() { return username; }

    public int getAccountBalance() { return accountBalance; }
}
