package com.User;

import com.UserDTO;
import com.stock.Stock;

import java.util.Objects;

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

    public UserDTO createDTO(){
        return  new UserDTO(CalcCurrentHoldings(),username, holdings.createDTO());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
