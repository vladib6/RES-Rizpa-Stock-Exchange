package com.Engine;

import com.Command.CmdTypes.CommandType;
import com.StockDTO;
import com.TransactionDTO;
import com.User.User;
import com.User.Userinterface;
import com.UserDTO;
import com.stock.Allstocks;
import com.stock.Stock;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name="rizpa-stock-exchange-descriptor")
public class MainEngine implements EngineInterface {

    public MainEngine(){
        allStocks= new Allstocks();
        userMap= new HashMap();
    }
   private Allstocks allStocks;
   private Userinterface connectedUser;
    private Map<String,User> userMap;

    public Map<String,User> getUserMap(){return userMap;}

    public Allstocks getAllStocks() {
        return allStocks;
    }

    public void Connect(Userinterface user){
        connectedUser=user;
    }

    public void addStock(Stock stock) throws StockException {
        if(allStocks.getAllStocks().containsKey(stock.getSymbol())){
            throw new StockException(stock.getSymbol(),"Symbol");
        }
        if(allStocks.isCompanyNameExist(stock.getCompanyName())){
            throw new StockException(stock.getCompanyName(),"Company name");
        }else{
            allStocks.addStock(stock);
        }
    }

    public void addUser(User user) throws Myexception {
        if(userMap.containsKey(user.getUsername())){
            throw new Myexception("The User: "+ user.getUsername() +" Already exist, each user must different username");
        }else{
            userMap.put(user.getUsername(),user);
        }
    }

    //Interface implement
    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }

    public StockDTO getStockDto(String symbol){
            Stock tempStock=allStocks.getStockByName(symbol);
            return tempStock.createStockDto();
    }

    public List<StockDTO> getAllstocksDto(){
        List<StockDTO> res=new ArrayList<>();
        for(Map.Entry<String,Stock> entry: allStocks.getAllStocks().entrySet()){
            res.add(entry.getValue().createStockDto());
        }

        return res;
    }

    public List<UserDTO> getAllUsersDto(){
        List<UserDTO> res=new ArrayList<>();
        for(Map.Entry<String,User> entry: userMap.entrySet()){
            res.add(entry.getValue().createDTO());
        }
        return res;
    }

    public UserDTO getUserDto(String username){
        return userMap.get(username).createDTO();
    }

    public Userinterface getConnectedUser() { return connectedUser; }

    public boolean isStockExist(String symbol){
        boolean res=false;
        for(Map.Entry<String,Stock> entry:getAllStocks().getAllStocks().entrySet()){
            if(entry.getKey()==symbol){
                res=true;
                return  res;
            }
        }
        return res;
    }

    public LinkedList<TransactionDTO> getTransactionListDtoByStock(String symbol){ return getStockByName(symbol).createTransactionDTOlist(); }

    public int ExecuteCmd(CommandType cmd){
       return cmd.Execute(getStockByName(cmd.getStockSymbol()));
    }
}
