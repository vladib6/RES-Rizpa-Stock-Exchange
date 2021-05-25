package GUI.Usertab;

import GUI.Afterloadscreen.AfterLoadScreenController;
import GUI.Commandform.CommandFormController;
import com.HoldingsDTO;
import com.UserDTO;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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

    private RotateTransition buttonRotate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Trade Button Animation
        buttonRotate= new RotateTransition();
        buttonRotate.setAxis(Rotate.Z_AXIS);
        buttonRotate.setByAngle(360);
        buttonRotate.setCycleCount(1);
        buttonRotate.setDuration(Duration.seconds(1));
        buttonRotate.setAutoReverse(true);
        buttonRotate.setNode(tradeButton);
        tradeButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                if(mainController.getAnimationState()){
                    buttonRotate.play();
                }
            }
        });
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
            loader.setLocation(getClass().getResource("/GUI/Commandform/CommandForm2.fxml"));
            try {
                Parent commandFormScene = loader.load();
                CommandFormController controller = loader.getController();
                controller.init(mainController);
                if(mainController.getAnimationState()){
                    commandFormScene.translateYProperty().set(anchorPane.getScene().getHeight());
                    anchorPane.getScene().setRoot(commandFormScene);
                    Timeline timeline= new Timeline();
                    KeyValue kv= new KeyValue(commandFormScene.translateYProperty(),0, Interpolator.EASE_IN);
                    KeyFrame kf= new KeyFrame(Duration.seconds(1),kv);
                    timeline.getKeyFrames().add(kf);
                    timeline.play();

                }else{
                    anchorPane.getScene().setRoot(commandFormScene);
                }
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



}
