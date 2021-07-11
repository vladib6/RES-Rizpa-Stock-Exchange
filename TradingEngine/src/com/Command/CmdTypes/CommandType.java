package com.Command.CmdTypes;

import com.Actions.Transaction;
import com.User.Traderinterface;
import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  abstract class CommandType {

   public CommandType(Traderinterface user, Direction direction, int numOfStocks, String stockName, int price){
      this.direction=direction;
      this.numOfStocks=numOfStocks;
      this.stockSymbol=stockName;
      this.price=price;
      this.time=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
      this.initiativeUser=user;
      staticid++;
      this.id=staticid;
   }
   Traderinterface initiativeUser;
   protected String stockSymbol;
   protected Direction direction;
   protected String time;
   protected int numOfStocks;
   protected int price;
   static int staticid=0;
   protected int id;
   public abstract int  Execute(Stock stock);

   //GETTERS

   public Traderinterface getInitiativeUser() { return initiativeUser; }

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
      return time+ "   Initiative User : "+initiativeUser.getUserName()+"   "+ stockSymbol+"   "+direction+ "   Stock quantity : "+numOfStocks+"   "+"Price : "+price +"   Turnover :"+ numOfStocks*price+"$";
   }

   public Transaction DoTransaction(CommandType Buy, CommandType Sell, int price, Stock stock){// create and return transaction and update commands details.
      stock.setCurrentPrice(price);//Set new stock price
      if(Buy.getNumOfStocks()>=Sell.getNumOfStocks()){
         int numOfRelevantStocks=Sell.getNumOfStocks();
         Buy.setNumOfStocks(Buy.getNumOfStocks()-Sell.getNumOfStocks());
         Sell.setNumOfStocks(0);
         Buy.getInitiativeUser().addHoldings(stock,numOfRelevantStocks);
         Sell.getInitiativeUser().removeHoldings(stock,numOfRelevantStocks);
         return new Transaction(price,getStockSymbol(),numOfRelevantStocks,price*numOfRelevantStocks,direction,Buy.getInitiativeUser().getUserName(),Sell.getInitiativeUser().getUserName());
      }else{
         int numOfRelevantStocks=Buy.getNumOfStocks();
         Sell.setNumOfStocks(Sell.getNumOfStocks()-Buy.getNumOfStocks());
         Buy.setNumOfStocks(0);
         Buy.getInitiativeUser().addHoldings(stock,numOfRelevantStocks);
         Sell.getInitiativeUser().removeHoldings(stock,numOfRelevantStocks);
         return new Transaction(price,getStockSymbol(),numOfRelevantStocks,price*numOfRelevantStocks,direction,Buy.getInitiativeUser().getUserName(),Sell.getInitiativeUser().getUserName());
      }
   }
}
