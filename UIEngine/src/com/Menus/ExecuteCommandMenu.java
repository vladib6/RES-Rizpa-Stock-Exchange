package com.Menus;

import com.Command.CmdTypes.*;
import com.Engine.MainEngine;
import com.Engine.Myexception;
import com.stock.Stock;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecuteCommandMenu {
    private MainEngine mainEngine;

    public ExecuteCommandMenu(MainEngine mainEngine){
        this.mainEngine=mainEngine;
    }
    public void RunMenu()throws Myexception {
        Command command= CreateCommand();
        int numOfTransactions=command.getCommand().Execute();

        if(numOfTransactions==0){//print message about transactions
            System.out.println("No Transactions Were performed");
        }else{
            System.out.println(numOfTransactions+"  Transactions Performed :");
            for(int i=0;i<numOfTransactions;i++){
                System.out.println("--> "+command.getCommand().getStock().getTransactionsList().get(i));
            }
        }

        if(command.getCommand().getNumOfStocks()==0){//print message about the command
            System.out.println("The whole command were performed");
        }else{
            System.out.println("Not all command were performed,\n ->> The command :" );
            System.out.println(command.getCommand());
            System.out.println("Added to waiting list");
        }
    }
    public Command CreateCommand()throws Myexception{//TODO: Handle exception in create command
        Direction direction = getDirectionFromUser();
        Type type=getTypeFromUser();
        String symbol=getSymbolFromUser();
        Stock stock= mainEngine.getStockByName(symbol);
        int numofstock=getNumOfStockFromUser();
        if(type==Type.LMT){
            int limitprice=getLimitPriceFromUser();
            return CommandFactory.Createcmd(direction,type,stock,numofstock,limitprice);
        }

        return CommandFactory.Createcmd(direction,type,stock,numofstock,0);
    }
    public Direction getDirectionFromUser() throws Myexception {
        Scanner scanner = new Scanner(System.in);
        int userchoice;
        boolean validchoise = false;
        System.out.println("Choose which command to execute");
        System.out.println("1 - SELL");
        System.out.println("2 - Buy");
        System.out.println("3 - Back to previous");

        while (!validchoise) {
            try {
                userchoice = scanner.nextInt();
                switch (userchoice){
                    case 1:
                        return Direction.SELL;
                    case 2:
                        return Direction.BUY;
                    case 3:
                        throw new Myexception("Back");
                    default:
                        System.out.println("Please choose a number according the menu");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter only numbers according to menu");
                System.out.println("Try Again");
                scanner.nextLine();
            }catch (Myexception e){
                throw e;
            }
        }
        return null;
    }

    public Type getTypeFromUser() throws Myexception {
        Scanner scanner = new Scanner(System.in);
        int userchoice;
        boolean validchoise = false;
        System.out.println("Choose the Type of command");
        System.out.println("1 - LMT");
        System.out.println("2 - MKT");
        System.out.println("3 - Back to previous");
//        System.out.println("3 - FOK");
//        System.out.println("4 - IOC");
        while (!validchoise) {
            try {
                userchoice = scanner.nextInt();
                switch (userchoice){
                    case 1:
                        return Type.LMT;
                    case 2:
                        return Type.MKT;
                    case 3:
                        throw new Myexception("Back");
                    default:
                        System.out.println("Please choose a number according the menu");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter only numbers according to menu");
                System.out.println("Try Again");
                scanner.nextLine();
            }catch (Myexception e){
                throw e;
            }
        }
        return null;

    }

    public String getSymbolFromUser() throws Myexception {
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        String symbol=null;
        System.out.println("Enter The name of Stock");
        while (!validchoise) {
            try {
               symbol= scanner.next();
               if(symbol.equals("q")){
                   throw new Myexception("Back");
               }
               if(mainEngine.getAllStocks().getAllStocks().containsKey(symbol.toUpperCase())){
                   return symbol.toUpperCase();
               }else{
                   System.out.println("There is no Stock with that name in the system \n  Try another name or type q  for back to previous menu");
               }
            } catch (InputMismatchException e) {
                System.out.println("Enter only numbers according to menu");
                System.out.println("Try Again, or type q  for back to previous menu");
                scanner.nextLine();
            }catch (Myexception e){
                throw  e;
            }
        }
        return symbol;
    }
    public int getNumOfStockFromUser() throws Myexception {
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        int numofstock=0;
        System.out.println("Enter number of stocks , Or type 0 for back to previous menu");

        while (!validchoise) {
            try {
              numofstock=scanner.nextInt();
              if(numofstock==0){
                  throw new Myexception("Back");
              }
              validchoise=true;

            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again , Or type 0 to back to previous menu");
            }catch (Myexception e){
                throw e;
            }
        }
        return numofstock;
    }

    public int getLimitPriceFromUser() throws Myexception {
        Scanner scanner = new Scanner(System.in);
        boolean validchoise = false;
        int limitprice=0;
        System.out.println("Enter Limit price for the command , or type 0 to back to previous menu");

        while (!validchoise) {
            try {
                limitprice=scanner.nextInt();
                if(limitprice==0){
                    throw new Myexception("Back");
                }
                validchoise=true;
            } catch (InputMismatchException e) {
                System.out.printf("Enter only numbers according to menu");
                System.out.printf("Try Again , or type 0 to back to previous menu");
            }catch (Myexception e){
                throw e;
            }
        }
        return limitprice;
    }


}
