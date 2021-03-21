package com.Command.CmdTypes;

import com.User.Userinterface;
import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  abstract class CommandType {

   public CommandType(Userinterface user,Direction direction, int numOfStocks, String stockName, int price){
      this.direction=direction;
      this.numOfStocks=numOfStocks;
      this.stockSymbol=stockName;
      this.price=price;
      this.time=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
      this.initiativeUser=user;
      staticid++;
      this.id=staticid;
   }
   Userinterface initiativeUser;
   protected String stockSymbol;
   protected Direction direction;
   protected String time;
   protected int numOfStocks;
   protected int price;
   static int staticid=0;
   protected int id;
   public abstract int  Execute(Stock stock);

   //GETTERS

   public Userinterface getInitiativeUser() { return initiativeUser; }

   public int getId(){return id; }
   public Direction getDirection() { return direction; }

   public String getTime() { return time; }

   public int getNumOfStocks() { return numOfStocks; }

   public String getStockSymbol() { return stockSymbol; }

   public int getPrice(){ return price; }

   //SETTERS

   public void setNumOfStocks(int numOfStocks) {
      this.numOfStocks = numOfStocks;
   }

   @Override
   public String toString(){
      return time+ "   Initiative User : "+initiativeUser.getUsername()+"   "+ stockSymbol+"   "+direction+ "   Stocks : "+numOfStocks+"   "+"Price : "+price +"   Turnover :"+ numOfStocks*price+"$";
   }


}
