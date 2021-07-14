package com.User;


import com.Engine.Myexception;
import com.OnlineUserDTO;
import com.UserAccountDTO;
import com.UserDTO;
import com.stock.Stock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllUsers {

    public AllUsers(){
        allUsers=new HashMap<>();
    }

    private HashMap<String,User> allUsers;

    synchronized public boolean addUser(User user){
        if(allUsers.containsKey(user.getUserName())){
            return false;
        }
        allUsers.put(user.getUserName(), user);
        return true;
    }

    public boolean removeUser(String name){
        if(allUsers.containsKey(name)) {
            allUsers.remove(name);
            return true;
        }
            return false;
    }

   public Traderinterface getTrader(String name){
        return (Traderinterface) allUsers.get(name);
    }

    public List<OnlineUserDTO> getConnected(){
        ArrayList<OnlineUserDTO> res=new ArrayList<>();
        for(Map.Entry<String, User> entry: allUsers.entrySet()){
            res.add(new OnlineUserDTO(entry.getKey(), entry.getValue().getUserType()));
        }
        return res;
    }

    public UserDTO getUserDTO(String name){//only for Trader user
        try{
            return ((Traderinterface)allUsers.get(name)).createDTO();
        }catch (NullPointerException e){
            throw e;
        }
    }

    public UserAccountDTO getUserAccountDTO(String name) throws Myexception {
            if(allUsers.containsKey(name)){
               return ((Traderinterface) allUsers.get(name)).createAccountDTO();
            }else{
                throw new Myexception("there is no user with that name");
            }
    }

    public boolean ChargeMoney(int amount,String user){
        if(allUsers.containsKey(user)){
           ((Traderinterface)allUsers.get(user)).ChargeMoney(amount);
           return true;
        }else{
            return  false;
        }
    }

    public void addHoldingToUser(String user,Stock stock,int quantity){
        ((Traderinterface)allUsers.get(user)).addHoldings(stock, quantity);
    }

    public int getStockHolding(String username,Stock stock){
        return ((Traderinterface)allUsers.get(username)).getStockHolding(stock);
    }
}
