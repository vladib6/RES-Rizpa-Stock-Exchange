package com.Menus;

import com.Command.CmdTypes.*;
import com.Engine.MainEngine;
import com.stock.Stock;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecuteCommandMenu {
    private MainEngine mainEngine;

    public ExecuteCommandMenu(MainEngine mainEngine){
        this.mainEngine=mainEngine;
    }
    public void RunMenu() {//TODO: complete progress of transaction execute
        Command command= CreateCommand();
        int numOfTransactions=command.getCommand().Execute();

        if(numOfTransactions==0){//print message about transactions
            System.out.println("No Transactions Were performed");
        }else{
            System.out.println(numOfTransactions+"  Transactions Performed");
            for(int i=0;i<numOfTransactions;i++){
                System.out.println(command.getCommand().getStock().getTransactionsList().get(i));
            }
        }

        if(command.getCommand().getNumOfStocks()==0){//print message about the command
            System.out.printf("The whole command were performed");
        }else{
            System.out.println("Not all command were performed, The command :" );
            System.out.println(command.getCommand());
            System.out.println("Added to waiting list");
        }
    }
    public Command CreateCommand(){
        Direction direction = getDirectionFromUser();
        Type type=getTypeFromUser();
        String symbol=getSymbolFromUser();
        Stock stock= mainEngine.getStockByName(symbol);
        int numofstock=getNumOfStockFromUser();
        int limitprice=getLimitPriceFromUser();

        return CommandFactory.Createcmd(direction,type,stock,numofstock,limitprice);
    }
    public Direction getDirectionFromUser() {
        Scanner scanner = new Scanner(System.in);
        int userchoice;
        boolean validchoise = false;
        System.out.println("Choose which command to execute");
        System.out.println("1 - SELL");
        System.out.println("2 - Buy");
        while (!validchoise) {
            try {
                userchoice = scanner.nextInt();
                if (userchoice == 1) {
                    return Direction.SELL;
                } else if (userchoice == 2) {
                    return Direction.BUY;
                } else {
                    System.out.println("Please choose a number according the menu");
                }
            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again");
            }
        }
        return null;
    }

    public Type getTypeFromUser() {
        Scanner scanner = new Scanner(System.in);
        int userchoice;
        boolean validchoise = false;
        System.out.println("Choose the Type of command");
        System.out.println("1 - LMT");
        System.out.println("2 - MKT");
//        System.out.println("3 - FOK");
//        System.out.println("4 - IOC");
        while (!validchoise) {
            try {
                userchoice = scanner.nextInt();
                if (userchoice == 1) {
                    return Type.LMT;
                } else if (userchoice == 2) {
                    return Type.MKT;
                } else {
                    System.out.println("Please choose a number according the menu");
                }
            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again");
            }
        }
        return null;

    }

    public String getSymbolFromUser(){
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        String symbol=null;
        System.out.println("Enter The name of Stock");
        while (!validchoise) {
            try {
               symbol= scanner.next();
               return symbol.toUpperCase();
            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again");

            }
        }
        return symbol;
    }
    public int getNumOfStockFromUser(){
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        int numofstock=0;
        System.out.println("Enter number of stocks");

        while (!validchoise) {
            try {
              numofstock=scanner.nextInt();
              validchoise=true;

            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again");
            }
        }
        return numofstock;
    }

    public int getLimitPriceFromUser(){
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        int limitprice=0;
        System.out.println("Enter Limit price for the command");

        while (!validchoise) {
            try {
                limitprice=scanner.nextInt();
                validchoise=true;

            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again");
            }
        }
        return limitprice;
    }


}
