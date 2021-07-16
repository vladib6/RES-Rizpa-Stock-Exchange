package com.User;

import com.Actions.ActionEntry;
import com.Actions.Transaction;
import com.AlertDTO;
import com.UserAccountDTO;
import com.UserDTO;
import com.stock.Stock;

import java.util.LinkedList;

public interface Traderinterface {
     String getUserName();
     int CalcCurrentHoldings();
     void addHoldings(Stock stock, int quantity);
     void removeHoldings(Stock stock, int quantity);
     void chargeMoney(int num);
     UserDTO createDTO();
     UserAccountDTO createAccountDTO();
     void ChargeMoney(int amount);
     void setActionsHistory(ActionEntry actionEntry);
     int getStockHolding(Stock stock);
     void addAlert(AlertDTO alert);
     void clearAlerts();
     LinkedList<AlertDTO> getAlerts() ;
}
