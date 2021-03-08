package com.Command.CmdTypes;

import com.Command.WaitingCommands.BuyWaitinglist;
import com.Command.WaitingCommands.SellWaitinglist;
import com.Transaction.Transaction;
import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  abstract class CommandType {

   public CommandType(Direction direction, int numOfStocks, Stock stock, int price){
      this.direction=direction;
      this.numOfStocks=numOfStocks;
      this.stock=stock;
      this.price=price;
      this.time=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
      id++;
   }

   protected Stock stock;
   protected Direction direction;
   protected String time;
   protected int numOfStocks;
   protected static int id=0;
   protected int price;

   public abstract int SellExecute();
   public abstract int  BuyExecute();

   public int  Execute(){
    if(direction==Direction.SELL){
     return SellExecute();
    }else{
     return BuyExecute();
    }
   }

   //GETTERS
   public Direction getDirection() { return direction; }

   public String getTime() { return time; }

   public int getNumOfStocks() { return numOfStocks; }

   public Stock getStock() { return stock; }

   public int getId() { return id; }

   public int getPrice(){ return price; }

   //SETTERS

   public void setNumOfStocks(int numOfStocks) {
      this.numOfStocks = numOfStocks;
   }

   @Override
   public String toString(){
      return time+ "   "+stock.getSymbol()+"   "+direction+ "   Stocks : "+numOfStocks+"   "+"Price : "+price +"   Turnover :"+ numOfStocks*price;
   }
}
