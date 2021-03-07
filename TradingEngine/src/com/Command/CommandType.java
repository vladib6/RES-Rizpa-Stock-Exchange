package com.Command;

import com.stock.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  abstract class CommandType {

   public CommandType(Direction direction,int numOfStocks,Stock stock,int price){
      this.direction=direction;
      this.numOfStocks=numOfStocks;
      this.stock=stock;
      this.price=price;
      this.time=DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now());
      id++;
   }

   private Stock stock;
   private Direction direction;
   private String time;
   private int numOfStocks;
   private static int id=0;
   private int price;

   public abstract void  EXE();
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
