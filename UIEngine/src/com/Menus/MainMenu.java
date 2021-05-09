package com.Menus;

import com.CommandDTO;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.StockDTO;
import com.TransactionDTO;
import com.load.Loadxml;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public MainMenu(){
        mainEngine=null;
        systemrunning=true;
    }
    private EngineInterface mainEngine;//The engine of trading system, store all classes and data that related to logics operations,stocks,commands,users and etc,if null so the data not loaded
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
                        File file= new File(filepath);
                        mainEngine= Loadxml.ParseXml(file);

                        System.out.println("<--- Loading Success ---> \n Now you Can Trade in the system");
                    }
                    catch (FileNotFoundException e ){
                        System.out.println("We can't find the file, please enter correct path or ensure that file exist");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Wrong Input");
                     } catch (StockException e) {
                        System.out.println(e);
                    } catch (Myexception e) {
                       System.out.println(e);
                      }catch (JAXBException  e) {
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
    public void menuafterload()  {
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
                    File file= new File(filepath);
                    mainEngine= Loadxml.ParseXml(file);
                    System.out.println("<--- Loading Success --->");
                    System.out.println("Now you can Trade on the system");
                } catch (FileNotFoundException e) {
                    System.out.println("We can't find the file, please enter correct path or ensure that file exist");
                }
                catch (InputMismatchException e){
                    System.out.println("Wrong Input");
                } catch (JAXBException e) {
                    System.out.println("The file not found or problem with the schema");
                }catch (StockException | Myexception e){
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
                    System.out.println("The stock doesn't exist");
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

        public void ExecuteCommand(EngineInterface mainEngine) throws Myexception {
            ExecuteCommandMenu executeCommandMenu=new ExecuteCommandMenu(mainEngine);
            executeCommandMenu.RunMenu();
    }

        public void DisplayAllStocks(){
             for(StockDTO dto: mainEngine.getAllstocksDto()){
                 System.out.println(dto);
             }
        }
        public void DisplayStock(String symbol){
            StockDTO stockDTO=mainEngine.getStockDto(symbol);
            System.out.println("<------ Stock Details ------>");
            System.out.println(stockDTO);
            System.out.println("<------ Stock Transactions ------>");
            ShowTransactionsByStock(stockDTO.getTransactionDTOS());
        }

        public void DisplayAllCommandsAndTranscation(){
            for (StockDTO stockDTO : mainEngine.getAllstocksDto()){
                System.out.println("-->  Stock Data : "+stockDTO.getSymbol()+ "  "+ stockDTO.getCompanyName()+"<--");
                System.out.println("<------- Transactions ------->");
                ShowTransactionsByStock(stockDTO.getTransactionDTOS());
                int total=0;
                for(TransactionDTO transactionDTO : stockDTO.getTransactionDTOS()){
                    total+=transactionDTO.getTurnover();
                }
                System.out.println("Total Transactions Turnover : "+total);
                System.out.println("<------- Waiting Buy Commands ------->");
                ShowWaitingCommands(stockDTO.getBuyWaiting());
                total=0;
                for(CommandDTO commandDTO : stockDTO.getBuyWaiting()){
                    total+=commandDTO.getPrice()*commandDTO.getNumOfStocks();
                }
                System.out.println("Total Buy Waiting Commands Turnover : "+total);
                System.out.println("<------- Waiting Sell Commands ------->");
                ShowWaitingCommands(stockDTO.getSellWaiting());
                total=0;
                for(CommandDTO commandDTO : stockDTO.getSellWaiting()){
                    total+=commandDTO.getPrice()*commandDTO.getNumOfStocks();
                }
                System.out.println("Total Sell Waiting Commands Turnover : "+total);
                System.out.println("");
                System.out.println("");
            }
        }

        public void ShowTransactionsByStock(List<TransactionDTO> transactionDTOList){
            if(transactionDTOList.size()==0){
                System.out.println("There is no Transaction for this stock yet");
            }else {
                Iterator<TransactionDTO> itr = transactionDTOList.iterator();
                while (itr.hasNext()) {
                    TransactionDTO transactionDTO = itr.next();
                    System.out.println(transactionDTO);
                }
            }
        }

        public void ShowWaitingCommands(List<CommandDTO> commandDTOList){
            if(commandDTOList.size()==0){
                System.out.println("There is no Waiting Sell Command for this stock yet");
            }else{
                for(CommandDTO dto: commandDTOList){
                    System.out.println(dto); }
            }
        }

    }



