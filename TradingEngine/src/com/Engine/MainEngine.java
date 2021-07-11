package com.Engine;

import com.Command.CmdTypes.*;
import com.OnlineUserDTO;
import com.StockDTO;
import com.TransactionDTO;
import com.User.*;
import com.UserDTO;
import com.load.Loadxml;
import com.stock.Allstocks;
import com.stock.Stock;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@XmlRootElement(name="rizpa-stock-exchange-descriptor")
public class MainEngine implements EngineInterface {

    public MainEngine(){
        allStocks= new Allstocks();
        allUsers=new AllUsers();
    }
    private Allstocks allStocks;
    private AllUsers allUsers;


    public Allstocks getAllStocks() {
        return allStocks;
    }



    public boolean addUser(User user) throws Myexception {
       return allUsers.addUser(user);
    }

    //Interface implement
    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }

    public StockDTO getStockDto(String symbol){
            Stock tempStock=allStocks.getStockByName(symbol);
            return tempStock.createStockDto();
    }


    public UserDTO getUserDto(String username){ return allUsers.getUserDTO(username); }


    @Override
    public List<StockDTO> getStocksInfo() {
        return allStocks.getStocksDTO();
    }

    @Override
    public Traderinterface getTrader(String name) {
        return allUsers.getTrader(name);
    }

    public boolean isStockExist(String symbol){ //check by symbol
        boolean res=false;
        for(Map.Entry<String,Stock> entry:allStocks.getAllStocks().entrySet()){
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


    public Command CreateAndExecuteCmd(String username,String direction,String stockSymbol,String cmdType,int quantity,int limitPrice){
        Direction dir;
        if(direction.equals("Sell")){
            dir=Direction.SELL;
        }else{
            dir=Direction.BUY;
        }
        Type type;
        if(cmdType.equals("LMT")){
            type=Type.LMT;
        }else{
            type=Type.MKT;
        }

        return CommandFactory.Createcmd(allUsers.getTrader(username),dir,type,stockSymbol,quantity,limitPrice);
    }

    @Override
    public boolean addUser(String name,String type) {
        User newUser;
        if(type.equals("Admin")){
            newUser=new Admin(name);
        }else{
            newUser=new Trader(name);
        }
        return allUsers.addUser(newUser);
    }

    @Override
    public boolean addStock(String companyName, String symbol, int price) {
        return allStocks.addStock(companyName, symbol, price);
    }

    @Override
    public List<OnlineUserDTO> getConnectedUsers() {
        return allUsers.getConnected();
    }

    @Override
    public void loadDataFromXml(InputStream inputStream,String username) throws FileNotFoundException, Myexception, StockException, JAXBException {
        Loadxml.ParseXml(inputStream,this,username);
    }
}
