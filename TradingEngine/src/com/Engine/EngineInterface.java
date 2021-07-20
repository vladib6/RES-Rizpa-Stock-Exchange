package com.Engine;

import com.*;
import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandType;
import com.User.Traderinterface;
import com.stock.Stock;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public interface EngineInterface {
    UserDTO getUserDto(String username);
    StockDTO getStockDto(String symbol);
    boolean isStockExist(String symbol);
    Stock getStockByName(String symbol);
    int ExecuteCmd(CommandType cmd);
    LinkedList<TransactionDTO> getTransactionListDtoByStock(String symbol);
    Command CreateAndExecuteCmd(String username,String direction, String stockSymbol, String cmdType, int quantity, int limitPrice);
    boolean addUser(String name,String type);
    boolean addStock(String companyName,String symbol,int price);
    List<OnlineUserDTO> getConnectedUsers();
    void loadDataFromXml(InputStream inputStream,String username) throws FileNotFoundException, Myexception, StockException, JAXBException;
    List<StockDTO> getStocksInfo();
    Traderinterface getTrader(String name);
    UserAccountDTO getUserAccount(String name) throws Myexception;
    boolean ChargeMoney(int amount,String name);
    void createNewStockByUser(String cName,String symbol,int price,int amount,String username) throws Myexception;
    int getStockHolding(String username,Stock stock);
    LinkedList<TransactionDTO> createTransactionsDtoList(String stockname);
    List<CommandDTO> getBuyCommands(String stockname);
    List<CommandDTO> getSellCommands(String stockname);
    LinkedList<AlertDTO> getAlerts(String username) ;
    LinkedList<ChartData> getChartData(String stockname);
}

