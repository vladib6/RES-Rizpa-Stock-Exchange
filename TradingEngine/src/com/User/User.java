package com.User;

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
    public int CalcCurrentHoldings(){

        return 0;
    }


}
