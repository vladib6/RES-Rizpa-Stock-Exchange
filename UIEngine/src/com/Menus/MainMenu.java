package com.Menus;
import com.Command.CmdTypes.CommandType;
import com.Engine.MainEngine;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.Transaction.Transaction;
import com.load.Loadxml;
import com.save.Savexml;
import com.stock.Stock;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMenu {
    public MainMenu(){
        mainEngine=null;
        systemrunning=true;
    }
    private MainEngine mainEngine;//The engine of trading system, store all classes and data that related to logics operations,stocks,commands,users and etc,if null so the data not loaded
    private boolean systemrunning;//if true the system run if false so turnoff.

    public void turnoff(){//turnoff the system
        System.out.println("Closing the system, Bye Bye...");
        systemrunning=false;
    }

    public void Run(){
        System.out.println("Welcome To --> RSE   Stock Exchange");

        while(systemrunning){
            if(mainEngine==null){
                try {
                    menubeforeload();
                }catch (InputMismatchException e){
                    System.out.println("Please enter only numbers according the Menu");
                }
            }
            else{
                try{
                    menuafterload();
                }catch (InputMismatchException e){
                    System.out.println("Please enter only numbers according the Menu");
                }
            }
        }
    }

    public void menubeforeload(){
        System.out.println("<------------------------------>");
        System.out.println("Please load data to the system");
        System.out.println("");
        System.out.println("Select option");
        System.out.println("1- Load from XML file");
        System.out.println("2-Exit");
        Scanner scanner=new Scanner(System.in);
        int userchoice=scanner.nextInt();

        switch (userchoice){
            case 1:
                    try{
                        System.out.println("Enter full path of XML file");
                        scanner.nextLine();
                        String filepath= scanner.nextLine();
                        mainEngine= Loadxml.ParseXml(filepath);
                        Savexml.save(mainEngine);
                        System.out.println("<--- Loading Success ---> \n Now you Can Trade in the system");
                    }
                    catch (FileNotFoundException e ){
                        System.out.println("We can't find the file, please enter correct path or ensure that file exist");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Wrong Input");
                     } catch (StockException e) {
                        System.out.println(e);
                    } catch (JAXBException  e) {
                        System.out.println("The file not found or problem with the schema");
                    }
                break;

            case 2:
                turnoff();
                break;
            default:
                System.out.println("Please select option from the menu");
                break;
        }
    }
    public void menuafterload()  {//TODO: check exceptions
        System.out.println("---------------");
        System.out.println("Select option");
        System.out.println("");
        System.out.println("1- Load from XML file");
        System.out.println("2-Display All Stocks");
        System.out.println("3-Display Choosen Stock");
        System.out.println("4-Execute Command");
        System.out.println("5-Display All Commands And Transcations");
        System.out.println("6-Exit");

        Scanner scanner=new Scanner(System.in);
        int userchoice=scanner.nextInt();

        switch (userchoice){
            case 1://Load from XML file
                try {
                    System.out.println("Enter full path of XML file");
                    scanner.nextLine();
                    String filepath=scanner.nextLine();
                    mainEngine= Loadxml.ParseXml(filepath);
                    System.out.println("<--- Loading Success --->");
                    System.out.println("Now you can Trade on the system");
                } catch (FileNotFoundException e) {
                    System.out.println("We can't find the file, please enter correct path or ensure that file exist");
                }
                catch (InputMismatchException e){
                    System.out.println("Wrong Input");
                } catch (JAXBException e) {
                    System.out.println("The file not found or problem with the schema");
                }catch (StockException e){
                    System.out.println(e.toString());
                }
                break;

            case 2://Display All Stocks
                DisplayAllStocks();
                break;

            case 3://Display Choosen Stock
                try{
                    System.out.println("Enter the name of stock");
                    String stockname=scanner.next();
                    DisplayStock(stockname.toUpperCase());
                }catch (InputMismatchException e){
                    System.out.println("Wrong input! , please enter symbol of stock");
                }
                catch (NullPointerException e){
                    System.out.println("The doesn't exist");
                }
                break;

            case 4://Execute Command
               try{
                   ExecuteCommand(mainEngine);
               }catch (InputMismatchException e){
                   System.out.println("You entered a Wrong input please sign only a valid chars according to orders in each step");
               }catch (NullPointerException e){
                   System.out.println("One or more of your details was wrong,\n Try Again with a correct details");
               }catch (Myexception e){
                   System.out.println(e.toString());
               }
                break;

            case 5://Display All Commands And Transcations
                DisplayAllCommandsAndTranscation();
                break;

            case 6://EXIT
                turnoff();
                break;
            default:
                System.out.println("Please select option from the menu");
                break;
        }
    }

        public void ExecuteCommand(MainEngine mainEngine) throws Myexception {
            ExecuteCommandMenu executeCommandMenu=new ExecuteCommandMenu(mainEngine);
            executeCommandMenu.RunMenu();
    }

         public void DisplayAllStocks(){
              for (Map.Entry<String,Stock> entry: mainEngine.getAllStocks().getAllStocks().entrySet()){
                  Stock stock= entry.getValue();
                  System.out.println(stock);
                  }
        }
        public void DisplayStock(String symbol){
            Stock stock=mainEngine.getStockByName(symbol);
            System.out.println("<------ Stock Details ------>");
            System.out.println(stock);
            System.out.println("<------ Stock Transactions ------>");
            ShowTransactionsByStock(stock);
        }

        public void DisplayAllCommandsAndTranscation(){
            for (Map.Entry<String,Stock> entry: mainEngine.getAllStocks().getAllStocks().entrySet()){
                Stock stock= entry.getValue();
                System.out.println("-->  Stock Data : "+stock.getSymbol()+ "  "+ stock.getCompanyName()+"<--");
                System.out.println("<------- Transactions ------->");
                ShowTransactionsByStock(stock);
                System.out.println("<------- Waiting Buy Commands ------->");
                ShowWaitingBuyCommands(stock);
                System.out.println("<------- Waiting Sell Commands ------->");
                ShowWaitingSellCommands(stock);
                System.out.println("");
                System.out.println("");
            }
        }

        public void ShowTransactionsByStock(Stock stock){
            if(stock.getTransactionsList().size()==0){
                System.out.println("There is no Transaction for this stock yet");
            }else {
                Iterator<Transaction> itr = stock.getTransactionsList().iterator();
                while (itr.hasNext()) {
                    Transaction transaction = itr.next();
                    System.out.println(transaction);
                }
            }
        }

        public void ShowWaitingSellCommands(Stock stock){
            List<CommandType> arrayList=stock.getSellWaitinglist().getSellwaitinglist();
            if(arrayList.size()==0){
                System.out.println("There is no Waiting Sell Command for this stock yet");
            }else{
                for(CommandType cmd: arrayList){
                    System.out.println(cmd);
                }
            }
        }
        public void ShowWaitingBuyCommands(Stock stock){
            List<CommandType> arrayList=stock.getBuyWaitinglist().getBuywaitinglist();
            if(arrayList.size()==0){
                System.out.println("There is no Waiting Buy Command for this stock yet");
            }else{
                for(CommandType cmd: arrayList){
                    System.out.println(cmd);
                }
            }

       }
    }



