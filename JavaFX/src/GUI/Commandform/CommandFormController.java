package GUI.Commandform;

import GUI.Afterloadscreen.AfterLoadScreenController;
import com.Command.CmdTypes.Command;
import com.HoldingsDTO;
import com.StockDTO;
import com.TransactionDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class CommandFormController implements Initializable {
    private Scene prevScene;
    private AfterLoadScreenController mainController;

    @FXML private AnchorPane anchorPane;
    @FXML private VBox vBox;
    @FXML private Label userLabel;
    @FXML private RadioButton Sell;
    @FXML private RadioButton Buy;
    @FXML private Pane directionPane;
    @FXML private RadioButton MKT;
    @FXML private RadioButton LMT;
    @FXML private TextField limitPriceTextFiled;
    @FXML private Label limitPriceLabel;
    @FXML private Pane typePane;
    @FXML private ComboBox<MenuItem> comboBox;
    @FXML private Pane chooseStockPane;
    @FXML private TextField quantityTextFiled;
    @FXML private Pane quantityPane;
    @FXML private Label quantityLabel;
    @FXML private CheckBox apllyCheckbox;
    @FXML private Button cancelButton;
    @FXML private Button executeButton;
    ToggleGroup directionToggleGroup=new ToggleGroup();
    ToggleGroup typeToggleGroup=new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sell.setToggleGroup(directionToggleGroup);
        Buy.setToggleGroup(directionToggleGroup);
        MKT.setToggleGroup(typeToggleGroup);
        LMT.setToggleGroup(typeToggleGroup);
        limitPriceTextFiled.setVisible(false);
        quantityLabel.setStyle("-fx-text-fill: red");
        limitPriceLabel.setStyle("-fx-text-fill: red");

        //mouse click actions set
        Sell.setOnMouseClicked(event -> { setItemsInCombobox("sell"); });
        Buy.setOnMouseClicked(event -> { setItemsInCombobox("buy"); });
        LMT.setOnMouseClicked(event -> { limitPriceTextFiled.setVisible(true); });
        MKT.setOnMouseClicked(event -> { limitPriceTextFiled.setVisible(false); });
        cancelButton.setOnMouseClicked(event -> {
            //back to previous scene
            Stage stage=(Stage) userLabel.getScene().getWindow();
            stage.setScene(prevScene);
            stage.show();
        });

        executeButton.setOnMouseClicked(event -> {
            try {
                executeButtonPressed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

      setListeners();
    }


    public void init(Scene prevScene,AfterLoadScreenController controller){
        this.prevScene=prevScene;
        mainController=controller;
        userLabel.setText("You trade as :" +mainController.getEngine().getConnectedUser().getUsername());
    }

    public void setItemsInCombobox(String type){
        if(type.equals("sell")){//show only stocks that user holds
            comboBox.getItems().clear();
            for(HoldingsDTO dto : mainController.getEngine().getUserDto(mainController.getEngine().getConnectedUser().getUsername()).getHoldingsDTOList()){
                MenuItem newitem= new MenuItem(dto.getSymbol()){
                    @Override
                    public String toString() {
                        return super.getText();
                    }
                };
                comboBox.getItems().add(newitem);
            }
        }else{//type==buy. show all stocks
            comboBox.getItems().clear();
            for(StockDTO dto : mainController.getEngine().getAllstocksDto()){
                MenuItem newitem= new MenuItem(dto.getSymbol()){
                    @Override
                    public String toString() {
                        return super.getText();
                    }
                };
                comboBox.getItems().add(newitem);
            }
        }
    }

    public void executeButtonPressed() throws IOException {
        int issuesCount=checkIssues();
        if(issuesCount==0) {//if no issues so execute the command
            String direction = ((RadioButton) directionToggleGroup.getSelectedToggle()).getText();
            String stock = comboBox.getSelectionModel().getSelectedItem().getText();
            String cmdType = ((RadioButton) typeToggleGroup.getSelectedToggle()).getText();
            int quantity=Integer.parseInt(quantityTextFiled.getText());
            int limitPrice = 0;
            if (cmdType.equals("LMT")) {
                limitPrice = Integer.parseInt(limitPriceTextFiled.getText());
            }
            Command cmd=mainController.getEngine().CreateAndExecuteCmd(direction,stock,cmdType, quantity,limitPrice);
            int numOfTransactions=mainController.getEngine().ExecuteCmd(cmd.getCommand());
            createAndShowAlert(numOfTransactions,cmd);
            backToScene();
        }
    }

    public boolean isStringContainOnlyDigits(String string){
        boolean res=true;
        try{
            int num=Integer.parseInt(string);
        }catch (NumberFormatException e){
            res=false;
        }
        return res;
    }
    public int checkIssues(){
        int count =0;
        //Direction selected?
        if (directionToggleGroup.getSelectedToggle()==null) {
            count++;
            directionPane.setStyle("-fx-border-color: red");
        }else{ directionPane.setStyle("-fx-border-color: none"); }
        //Stock selected?
        if(comboBox.getSelectionModel().getSelectedItem()==null){
            count++;
            chooseStockPane.setStyle("-fx-border-color: red");
        }else{ chooseStockPane.setStyle("-fx-border-color: none"); }
        //command type selected?
        if(typeToggleGroup.getSelectedToggle()==null){
            count++;
            typePane.setStyle("-fx-border-color: red");
        }else if(typeToggleGroup.getSelectedToggle().equals(LMT)){
            typePane.setStyle("-fx-border-color: none");
            if(limitPriceTextFiled.getText().equals("") || !isStringContainOnlyDigits(limitPriceTextFiled.getText()) || Integer.parseInt(limitPriceTextFiled.getText())<=0){
                count++;
                limitPriceTextFiled.setStyle("-fx-border-color: red");
            }else{
                limitPriceTextFiled.setStyle("-fx-border-color: none");
            }
        }
        //Quantity selected?
        if(quantityTextFiled.getText().equals("")){
            count++;
            quantityPane.setStyle("-fx-border-color: red");
        }else if(directionToggleGroup.getSelectedToggle().equals(Sell) && comboBox.getSelectionModel().getSelectedItem()!=null){
            if(!checkValidQuantity(quantityTextFiled.getText())){
                count++;
            }
        }else{
            if(!isStringContainOnlyDigits(quantityTextFiled.getText())|| Integer.parseInt(quantityTextFiled.getText())<=0){
                count++;
                quantityPane.setStyle("-fx-border-color: red");
            }else{
                quantityPane.setStyle("-fx-border-color: none");
            }
        }
        //aplly selected?
        if(!apllyCheckbox.isSelected()){
            count++;
            apllyCheckbox.setStyle("-fx-border-color: red");
        }else{apllyCheckbox.setStyle("-fx-border-color: none"); }

        return count;
    }

    public boolean checkValidQuantity(String string){
        boolean res=true;
        int quantity=Integer.parseInt(string);
        String selectedStock=comboBox.getSelectionModel().getSelectedItem().getText();
        int userCurrentQuantity = mainController.getEngine().getUserDto(mainController.getEngine().getConnectedUser().getUsername()).getHoldingsDtoByStock(selectedStock).getQuantity();
            if(quantity>userCurrentQuantity || quantity<=0) {
                res = false;
                quantityLabel.setText("0 < Quantity <= " + userCurrentQuantity);
            }
          else{
                quantityLabel.setText("");
            }

            return res;
        }

    public void createAndShowAlert(int numOfTransactions, Command cmd){//TODO:fix the size of alert box
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Command Execute Information");

        if(numOfTransactions==0){
            alert.setContentText("No Transactions Were performed");
        }else{
            alert.setContentText(numOfTransactions+"  Transactions Performed :");
            LinkedList<TransactionDTO> transactionsList=mainController.getEngine().getTransactionListDtoByStock(cmd.getCommand().getStockSymbol());
            for(int i=0;i<numOfTransactions;i++){
                alert.setContentText(alert.getContentText()+"\n--> "+transactionsList.get(i));
            }
        }

        if(cmd.getCommand().getNumOfStocks()==0){//print message about the command
            alert.setContentText(alert.getContentText()+ "\n"+"The whole command were performed");
        }else{
            alert.setContentText(alert.getContentText()+"\n"+ "Not all command were performed,\n ->> The command :" );
            alert.setContentText(alert.getContentText()+"\n"+ cmd.getCommand().toString());
            alert.setContentText(alert.getContentText()+ "\n"+"Added to waiting list");
        }
        alert.setResizable(true);
        alert.showAndWait();

    }

    public void backToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Afterloadscreen/AfterLoadScreen.fxml"));
        Parent userScene = loader.load();
        Scene newScene = new Scene(userScene, 800, 600);
        AfterLoadScreenController controller = loader.getController();
        controller.initEngine(mainController.getEngine());
        Stage window = ((Stage)anchorPane.getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public void setListeners(){
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                chooseStockPane.setStyle("-fx-border-color: none");
            }
        });

        typeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isSelected()){
                typePane.setStyle("-fx-border-color: none");
            }
        });

        directionToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isSelected()){
                directionPane.setStyle("-fx-border-color: none");
            }
        });

        limitPriceTextFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!isStringContainOnlyDigits(newValue)){
                limitPriceLabel.setVisible(true);
                limitPriceLabel.setText("Digits only !");
            }else if(Integer.parseInt(newValue)<=0){
                limitPriceLabel.setText("0 < Limit Price");
            }else{
                limitPriceLabel.setText("");
            }
        });
        quantityTextFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!isStringContainOnlyDigits(newValue)){
                quantityLabel.setVisible(true);
                quantityLabel.setText("Digits only !");
            }else if(directionToggleGroup.getSelectedToggle().equals(Sell) && comboBox.getSelectionModel().getSelectedItem()!=null) {
                checkValidQuantity(newValue);
            }else if(Integer.parseInt(newValue)<=0){
                quantityLabel.setText("0 < Quantity");
            }
            else {
                quantityLabel.setText("");
            }
        });
    }

    }

