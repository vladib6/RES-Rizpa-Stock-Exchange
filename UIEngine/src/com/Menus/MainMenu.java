package com.Menus;
import com.Command.CmdTypes.Buycmd;
import com.Command.CmdTypes.Command;
import com.Command.CmdTypes.CommandType;
import com.Command.CmdTypes.Sellcmd;
import com.Transaction.Transaction;
import com.load.*;
import com.Engine.MainEngine;
import com.stock.Stock;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MainMenu {
    public MainMenu(){
        mainEngine=null;
        systemrunning=true;
    }
    private MainEngine mainEngine;//The engine of trading system, store all classes and data that related to logics operations,stocks,commands,users and etc,if null so the data not loaded
    private boolean systemrunning;//if true the system run if false so turnoff.

    public void turnoff(){//turnof the system
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
                        mainEngine= new Loadxml().ParseXml(filepath);
                    }
                    catch (FileNotFoundException e ){
                        System.out.println("We can't find the file, please enter correct path or ensure that file exist");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Wrong Input");
                     } catch (SAXException e) {
                        System.out.println("SaxException");
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                break;

            case 2:
                turnoff();
                break;
            default:
                System.out.printf("Please select option from the menu");
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
                    mainEngine= new Loadxml().ParseXml(filepath);
                } catch (FileNotFoundException e) {
                    System.out.printf("We can't find the file, please enter correct path or ensure that file exist");
                }
                catch (InputMismatchException e){
                    System.out.println("Wrong Input");
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.printf("Cannot Open the file, check is name or the path");
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
                    System.out.printf("Wrong input! , please enter symbol of stock");
                }
                catch (NullPointerException e){
                    System.out.println("The doesn't exist");
                }
                break;

            case 4://Execute Command
               try{
                   ExecuteCommand(mainEngine);
               }catch (InputMismatchException e){
                   System.out.printf("You entered a Wrong input please sign only a valid chars according to orders in each step");
               }catch (NullPointerException e){
                   System.out.println("One or more of your details was wrong,\n Try Again with a correct details");
               }
                break;

            case 5://Display All Commands And Transcations
                DisplayAllCommandsAndTranscation();
                break;

            case 6://EXIT
                turnoff();
                break;
            default:
                System.out.printf("Please select option from the menu");
                break;
        }
    }

        public void ExecuteCommand(MainEngine mainEngine){
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
            System.out.println(stock);
            ShowTransactionsByStock(stock);
        }

        public void DisplayAllCommandsAndTranscation(){
            for (Map.Entry<String,Stock> entry: mainEngine.getAllStocks().getAllStocks().entrySet()){
                Stock stock= entry.getValue();
                System.out.println(stock.getId()+"-  Stock Data : "+stock.getSymbol()+ "  "+ stock.getCompanyName());
                ShowTransactionsByStock(stock);
                ShowWaitingBuyCommands(stock);
                ShowWaitingSellCommands(stock);
            }
        }

        public void ShowTransactionsByStock(Stock stock){
            Iterator<Transaction> itr=stock.getTransactionsList().iterator();
            while(itr.hasNext()){
                Transaction transaction= itr.next();
                System.out.println(transaction);            }
        }

        public void ShowWaitingSellCommands(Stock stock){
            ArrayList<CommandType> arrayList=stock.getSellWaitinglist().getSellwaitinglist();
            for(CommandType cmd: arrayList){
                System.out.println(cmd);            }
        }
        public void ShowWaitingBuyCommands(Stock stock){
            ArrayList<CommandType> arrayList=stock.getBuyWaitinglist().getBuylwaitinglist();
            for(CommandType cmd: arrayList){
                System.out.println(cmd);
            }
       }
    }



