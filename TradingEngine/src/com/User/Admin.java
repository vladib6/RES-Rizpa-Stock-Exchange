package com.User;

public class Admin implements User{

    public Admin(String name){
        this.userName=name;
    }

    String userName;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUserType() {
       return  Admin.class.getSimpleName();
    }


}
