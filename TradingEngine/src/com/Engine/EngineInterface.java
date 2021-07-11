package com.Engine;

import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandType;
import com.OnlineUserDTO;
import com.StockDTO;
import com.TransactionDTO;
import com.User.Traderinterface;
import com.UserDTO;
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
    }

