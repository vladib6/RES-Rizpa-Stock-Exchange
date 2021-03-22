package com.User;

import com.stock.Stock;

public class User implements  Userinterface{

   public User(String username){
       holdings=new Holdings();
       this.username=username;
   }

   String username;
   Holdings holdings;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int CalcCurrentHoldings(){ return  holdings.currentHoldings(); }

    @Override
    public void addHoldings(Stock stock, int quantity) {
        holdings.addToHoldings(stock, quantity);
    }

    @Override
    public void removeHoldings(Stock stock, int quantity) {
        holdings.removeFromHoldings(stock, quantity);
    }

}
