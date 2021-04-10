package GUI;

import com.HoldingsDTO;
import com.StockDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CommandFormController implements Initializable {
    private Scene prevScene;
    private AfterLoadScreenController mainController;

    @FXML private AnchorPane anchorPane;
    @FXML private VBox vBox;
    @FXML private Label userLabel;
    @FXML private Label errMessage;
    @FXML private RadioButton Sell;
    @FXML private RadioButton Buy;
    @FXML private Pane directionPane;
    @FXML private RadioButton MKT;
    @FXML private RadioButton LMT;
    @FXML private TextField limitPriceTextFiled;
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

        executeButton.setOnMouseClicked(event -> { executeButtonPressed(); });

        quantityTextFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!isStringContainOnlyDigits(newValue)){
                quantityLabel.setVisible(true);
                quantityLabel.setText("Digits only !");
            }else if(directionToggleGroup.getSelectedToggle()!=null && comboBox.getSelectionModel().getSelectedItem()!=null) {
                checkValidQuantity(newValue);
            }
            else {
                quantityLabel.setText("");
            }
        });
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

    public void executeButtonPressed(){
              int issuesCount=checkIssues();

//            String direction = ((RadioButton) directionToggleGroup.getSelectedToggle()).getText();
//            String stock = comboBox.getSelectionModel().getSelectedItem().getText();
//            String cmdType = ((RadioButton) typeToggleGroup.getSelectedToggle()).getText();
//            System.out.println("direction :"+direction+" stock: "+stock+" cmdtype :"+cmdType);
//            int limitPrice = 0;
//            if (cmdType.equals("LMT")) {
//                limitPrice = Integer.parseInt(limitPriceTextFiled.getText());
//            }
//        }catch (NumberFormatException e){
//
//        }
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
        if (directionToggleGroup.getSelectedToggle()==null)
        {
            count++;
            directionPane.setStyle("-fx-border-color: red");
        }else{
            directionPane.setStyle("-fx-border-color: none");

        }
        if(comboBox.getSelectionModel().getSelectedItem()==null){
            count++;
            chooseStockPane.setStyle("-fx-border-color: red");
        }else{
            chooseStockPane.setStyle("-fx-border-color: none");
        }
        if(typeToggleGroup.getSelectedToggle()==null){
            count++;
            typePane.setStyle("-fx-border-color: red");
        }else{
            typePane.setStyle("-fx-border-color: none");
            if(limitPriceTextFiled.getText().equals("")){
                count++;
                limitPriceTextFiled.setStyle("-fx-border-color: red");
            }else{
                limitPriceTextFiled.setStyle("-fx-border-color: none");
            }
        }
        if(quantityTextFiled.getText().equals("")){
            count++;
            quantityPane.setStyle("-fx-border-color: red");
        }else if(directionToggleGroup.getSelectedToggle()!=null && comboBox.getSelectionModel().getSelectedItem()!=null){
            checkValidQuantity(quantityTextFiled.getText());
        }

        if(!apllyCheckbox.isSelected()){
            count++;
            apllyCheckbox.setStyle("-fx-border-color: red");
        }else{
            apllyCheckbox.setStyle("-fx-border-color: none");
        }

        return count;
    }

    public void checkValidQuantity(String string){
        int quantity=Integer.parseInt(string);
        String selectedStock=comboBox.getSelectionModel().getSelectedItem().getText();
        int userCurrentQuantity = mainController.getEngine().getUserDto(mainController.getEngine().getConnectedUser().getUsername()).getHoldingsDtoByStock(selectedStock).getQuantity();
            if(quantity>userCurrentQuantity){
                quantityLabel.setText("You can sell at most "+userCurrentQuantity+" stocks");
            }else if(quantity<=0){
                quantityLabel.setText("Negative number is not valid");
            }else{
                quantityLabel.setText("");
            }
        }
    }

