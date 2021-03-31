package GUI;

import com.Engine.EngineInterface;
import com.HoldingsDTO;
import com.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewTabController implements Initializable {
    private EngineInterface engine;

    @FXML
    TableView tableView;

    @FXML
    TableColumn<HoldingsDTO,String> symbol;

    @FXML
    TableColumn<HoldingsDTO,String> quantity;
    @FXML
    TableColumn<HoldingsDTO,String> price;

    @FXML
    Button tradeButton;

    @FXML
    Label username;

    @FXML
    Label holdingsValue;

    @FXML
    VBox vBox;

    @FXML
    AnchorPane anchorPane;

    @FXML ImageView userImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.prefWidthProperty().bind(anchorPane.widthProperty());
        vBox.prefWidthProperty().bind(anchorPane.widthProperty());
        username.prefWidthProperty().bind(vBox.widthProperty());
        holdingsValue.prefWidthProperty().bind(vBox.widthProperty());

        tableView.setPlaceholder(new Label("There is no holdings to this user"));
        symbol.setText("Symbol");
        quantity.setText("Quantity");
        price.setText("Price($)");
        ImageView tradeImg=new ImageView("./Resources/exchange.jpg");
        tradeImg.setFitHeight(34);
        tradeImg.setPreserveRatio(true);
        tradeImg.setFitWidth(35);
        tradeButton.setGraphic(tradeImg);


    }

    public void initUserTab(EngineInterface engine,UserDTO dto){
        username.setText(dto.getUsername());
        holdingsValue.setText(String.valueOf(dto.getTotalHoldings()+" $ "));

        //init table view
        symbol.setCellValueFactory( new PropertyValueFactory<>("symbol"));
        quantity.setCellValueFactory( new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory( new PropertyValueFactory<>("stockPrice"));
        tableView.getColumns().clear();
        tableView.getColumns().addAll(symbol,quantity,price);

        for(HoldingsDTO hDto: dto.getHoldingsDTOList()){
            tableView.getItems().add(hDto);
        }

    }
}
