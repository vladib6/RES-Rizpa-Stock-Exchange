package GUI.Usertab;

import GUI.Afterloadscreen.AfterLoadScreenController;
import GUI.Commandform.CommandFormController;
import com.HoldingsDTO;
import com.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserViewTabController implements Initializable {
    private AfterLoadScreenController mainController;

    @FXML private TableView<HoldingsDTO> tableView;
    @FXML private TableColumn<HoldingsDTO,String> symbol;
    @FXML private TableColumn<HoldingsDTO,String> quantity;
    @FXML private TableColumn<HoldingsDTO,String> price;
    @FXML private Button tradeButton;
    @FXML private Label username;
    @FXML private Label holdingsValue;
    @FXML private VBox vBox;
    @FXML private HBox hBox;
    @FXML private AnchorPane anchorPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //view property
        tableView.prefWidthProperty().bind(anchorPane.widthProperty());
        vBox.prefWidthProperty().bind(anchorPane.widthProperty());
        hBox.prefWidthProperty().bind(anchorPane.widthProperty());
        username.prefWidthProperty().bind(vBox.widthProperty());
        holdingsValue.prefWidthProperty().bind(vBox.widthProperty());

        //table view
        tableView.setPlaceholder(new Label("There is no holdings to this user"));
        symbol.setText("Symbol");
        quantity.setText("Quantity");
        price.setText("Price($)");
        symbol.setCellValueFactory( new PropertyValueFactory<>("symbol"));
        quantity.setCellValueFactory( new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory( new PropertyValueFactory<>("stockPrice"));

        tradeButton.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Commandform/CommandForm.fxml"));
            try {
                Parent commandFormScene = loader.load();
                Scene newScene = new Scene(commandFormScene, 500, 600);
                CommandFormController controller = loader.getController();
                controller.init(currentScene(),mainController);
                Stage window = ((Stage)anchorPane.getScene().getWindow());
                window.setScene(newScene);
                window.show();
            } catch (IOException e) {
                mainController.updateMessage(e.toString());
            }

        });
    }

    public void injectMainController(AfterLoadScreenController controller){
        mainController=controller;
    }

    public void initUserTab(UserDTO dto){
        username.setText(dto.getUsername());
        holdingsValue.setText(dto.getTotalHoldings() + " $ ");

        //init table view
        tableView.getColumns().clear();
        tableView.getColumns().addAll(symbol,quantity,price);
        tableView.getItems().addAll(dto.getHoldingsDTOList());
    }

    public Scene currentScene(){ return anchorPane.getScene(); }
}
