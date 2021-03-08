package com.Transaction;

import java.util.HashMap;
import java.util.LinkedList;

public class Alltransactions {
    public Alltransactions() {

        allTransactionList =new HashMap<String,LinkedList<Transaction>>() ;
    }

    private HashMap<String, LinkedList<Transaction>> allTransactionList;


    public HashMap<String, LinkedList<Transaction>> getallTransactionList() {
        return allTransactionList;
    }

    public void addTransaction(String symbol,Transaction transaction){
        allTransactionList.get(symbol).add(transaction);
    }

    public void addCellToHashMap(String symbol,LinkedList<Transaction> lt){
        allTransactionList.put(symbol,lt);
    }

}
