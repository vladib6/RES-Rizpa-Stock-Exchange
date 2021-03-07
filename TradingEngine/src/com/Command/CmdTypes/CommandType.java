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

   private Stock stock;
   protected Direction direction;
   private String time;
   private int numOfStocks;
   private static int id=0;
   private int price;

   public abstract Transaction FindSellcmd(SellWaitinglist sellWaitinglist);
   public abstract Transaction  FindBuycmd(BuyWaitinglist buyWaitinglist);
   public Transaction Findcmd(Stock stock){
    if(direction==Direction.SELL){
      return FindBuycmd(stock.getBuyWaitinglist());
    }else{
      return FindSellcmd(stock.getSellWaitinglist());
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

}
