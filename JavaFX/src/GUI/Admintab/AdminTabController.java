package GUI.Admintab;

import GUI.Afterloadscreen.AfterLoadScreenController;
import com.CommandDTO;
import com.StockDTO;
import com.TransactionDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminTabController implements Initializable {
    private AfterLoadScreenController mainController;

    @FXML private ComboBox<MenuItem> comboBox;
    @FXML private Label topLabel;
    @FXML private AnchorPane anchorPane;
    @FXML private Accordion accordion;

    @FXML private TableView BuyCmdtableView;
    @FXML private TableView SellCmdtableView;
    @FXML private TableView transactionstableView;

    @FXML private TableColumn<CommandDTO,String> buyCmdDate;
    @FXML private TableColumn<CommandDTO,String> buyCmdType;
    @FXML private TableColumn<CommandDTO,String> buyCmdQuantity;
    @FXML private TableColumn<CommandDTO,String> buyCmdPrice;
    @FXML private TableColumn<CommandDTO,String> buyCmdUser;

    @FXML private TableColumn<CommandDTO,String> sellCmdDate;
    @FXML private TableColumn<CommandDTO,String> sellCmdType;
    @FXML private TableColumn<CommandDTO,String> sellCmdQuantity;
    @FXML private TableColumn<CommandDTO,String> sellCmdPrice;
    @FXML private TableColumn<CommandDTO,String> sellCmdUser;

    @FXML private TableColumn<TransactionDTO,String> transactionDate;
    @FXML private TableColumn<TransactionDTO,String> transactionQuantity;
    @FXML private TableColumn<TransactionDTO,String> transactionPrice;
    @FXML private TableColumn<TransactionDTO,String> transactionTurnover;
    @FXML private TableColumn<TransactionDTO,String> transactionBuyUser;
    @FXML private TableColumn<TransactionDTO,String> transactionSellUser;

    @FXML private LineChart<String,Number> lineChart;
    @FXML private NumberAxis yAxis;
    @FXML private CategoryAxis xAxis;
    private XYChart.Series<String,Number> series= new XYChart.Series<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topLabel.prefWidthProperty().bind(anchorPane.widthProperty());
        comboBox.setOnAction(event -> showDataOfStock(comboBox.getSelectionModel().getSelectedItem().getText()));
        initializeTablesView();
        lineChart.getData().add(series);
    }
    public void injectMainController(AfterLoadScreenController controller){
        mainController=controller;
    }

    public void initData(){//init combobox items according to stocks
        for(StockDTO dto: mainController.getEngine().getAllstocksDto()){
            MenuItem newItem=new MenuItem(dto.getSymbol()){
                @Override
                public String toString() {
                    return super.getText();
                }
            };
            comboBox.getItems().add(newItem);
        }

    }

    public void showDataOfStock(String stockName){
        StockDTO stockDTO=mainController.getEngine().getStockDto(stockName);
        //update line chart
        series.getData().clear();
        for(TransactionDTO dto: stockDTO.getTransactionDTOS()){
            series.getData().add(0,new XYChart.Data<>(dto.getDate(), dto.getTurnover()));
        }

        //update tables view
        BuyCmdtableView.getItems().clear();
        BuyCmdtableView.getItems().addAll(stockDTO.getBuyWaiting());
        SellCmdtableView.getItems().clear();
        SellCmdtableView.getItems().addAll(stockDTO.getSellWaiting());
        transactionstableView.getItems().clear();
        transactionstableView.getItems().addAll(stockDTO.getTransactionDTOS());

    }

    public void initializeTablesView(){
        BuyCmdtableView.setPlaceholder(new Label("No waiting commands yet"));
        buyCmdDate.setText("Date");
        buyCmdDate.setCellValueFactory(new PropertyValueFactory<>("time"));
        buyCmdType.setText("Type");
        buyCmdType.setCellValueFactory(new PropertyValueFactory<>("type"));
        buyCmdQuantity.setText("Quantity");
        buyCmdQuantity.setCellValueFactory(new PropertyValueFactory<>("numOfStocks"));
        buyCmdPrice.setText("Price");
        buyCmdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyCmdUser.setText("User");
        buyCmdUser.setCellValueFactory(new PropertyValueFactory<>("initiativeUser"));
        BuyCmdtableView.getColumns().clear();
        BuyCmdtableView.getColumns().addAll(buyCmdDate,buyCmdType,buyCmdQuantity,buyCmdPrice,buyCmdUser);

        SellCmdtableView.setPlaceholder(new Label("No waiting commands yet"));
        sellCmdDate.setText("Date");
        sellCmdDate.setCellValueFactory(new PropertyValueFactory<>("time"));
        sellCmdType.setText("Type");
        sellCmdType.setCellValueFactory(new PropertyValueFactory<>("type"));
        sellCmdQuantity.setText("Quantity");
        sellCmdQuantity.setCellValueFactory(new PropertyValueFactory<>("numOfStocks"));
        sellCmdPrice.setText("Price");
        sellCmdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellCmdUser.setText("User");
        sellCmdUser.setCellValueFactory(new PropertyValueFactory<>("initiativeUser"));
        SellCmdtableView.getColumns().clear();
        SellCmdtableView.getColumns().addAll(sellCmdDate,sellCmdType, sellCmdQuantity,sellCmdPrice,sellCmdUser);


        transactionstableView.setPlaceholder(new Label("No transactions yet"));
        transactionDate.setText("Date");
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        transactionQuantity.setText("Quantity");
        transactionQuantity.setCellValueFactory(new PropertyValueFactory<>("numOfStock"));
        transactionPrice.setText("Price");
        transactionPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        transactionTurnover.setText("Turnover");
        transactionTurnover.setCellValueFactory(new PropertyValueFactory<>("turnover"));
        transactionSellUser.setText("Seller");
        transactionSellUser.setCellValueFactory(new PropertyValueFactory<>("seller"));
        transactionBuyUser.setText("Buyer");
        transactionBuyUser.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        transactionstableView.getColumns().clear();
        transactionstableView.getColumns().addAll(transactionDate,transactionQuantity,transactionPrice,transactionTurnover,transactionSellUser,transactionBuyUser);
    }
}
