package com.Menus;

import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandFactory;
import com.Command.CmdTypes.Direction;
import com.Command.CmdTypes.Type;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.TransactionDTO;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ExecuteCommandMenu {
    private EngineInterface mainEngine;

    public ExecuteCommandMenu(EngineInterface mainEngine){
        this.mainEngine=mainEngine;
    }
    public void RunMenu()throws Myexception {
        Command command= CreateCommand();
        int numOfTransactions=mainEngine.ExecuteCmd(command.getCommand());

        if(numOfTransactions==0){//print message about transactions
            System.out.println("No Transactions Were performed");
        }else{
            System.out.println(numOfTransactions+"  Transactions Performed :");
            LinkedList<TransactionDTO> transactionsList=mainEngine.getTransactionListDtoByStock(command.getCommand().getStockSymbol());
            for(int i=0;i<numOfTransactions;i++){
                System.out.println("--> "+transactionsList.get(i));
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
    public Command CreateCommand()throws Myexception{
        Direction direction = getDirectionFromUser();
        String stockSymbol=getSymbolFromUser();
        Type type=getTypeFromUser();
        int numofstock=getNumOfStockFromUser();
        if(type==Type.LMT){
            int limitprice=getLimitPriceFromUser();
            return CommandFactory.Createcmd(mainEngine.getConnectedUser(), direction,type,stockSymbol,numofstock,limitprice);
        }
        return CommandFactory.Createcmd(mainEngine.getConnectedUser(),direction,type,stockSymbol,numofstock,0);
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
               if((!mainEngine.isStockExist(symbol.toUpperCase()))){
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
                System.out.println("Enter only numbers according to menu");
                System.out.println("Try Again , Or type 0 to back to previous menu");
                scanner.nextLine();
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
                System.out.println("Enter only numbers according to menu");
                System.out.println("Try Again , or type 0 to back to previous menu");
                scanner.nextLine();
            }catch (Myexception e){
                throw e;
            }
        }
        return limitprice;
    }


}
