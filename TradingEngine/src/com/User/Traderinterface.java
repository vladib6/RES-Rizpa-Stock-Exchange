package com.User;

import com.Actions.ActionEntry;
import com.UserAccountDTO;
import com.UserDTO;
import com.stock.Stock;

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
}
