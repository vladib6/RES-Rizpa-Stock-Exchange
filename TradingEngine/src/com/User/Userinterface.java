package com.User;

import com.stock.Stock;

public interface Userinterface {

     String getUsername();
     int CalcCurrentHoldings();
     void addHoldings(Stock stock, int quantity);
     void removeHoldings(Stock stock, int quantity);

}
