package GUI.Afterloadscreen;

import GUI.Admintab.AdminTabController;
import GUI.Usertab.UserViewTabController;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoadScreenController implements Initializable {
    private EngineInterface Engine;
    private  boolean animation;
    @FXML private BorderPane borderPane;
    @FXML private TabPane tabPane;
    @FXML private Label errMessage;
    @FXML private ToggleGroup themeToggleGroup;
    @FXML private ToggleButton classicMode;
    @FXML private ToggleButton lightMode;
    @FXML private ToggleButton darkMode;
    @FXML private CheckBox animationBox;
    @FXML private ScrollPane scrollPane;

    final String classicThemePath= getClass().getResource("../main/mainscreenstyle.css").toExternalForm();
    final String lightThemePath= getClass().getResource("../main/light-mode.css").toExternalForm();
    final String darkThemePath= getClass().getResource("../main/dark-mode.css").toExternalForm();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animation=false;
        animationBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            animation=newValue;
        });

        themeToggleGroup=new ToggleGroup();
        themeToggleGroup.getToggles().addAll(classicMode,lightMode,darkMode);
        themeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(classicMode.isSelected()){
                borderPane.getScene().getStylesheets().clear();
                borderPane.getScene().getStylesheets().add(classicThemePath);
            }else if(lightMode.isSelected()){
                borderPane.getScene().getStylesheets().clear();
                borderPane.getScene().getStylesheets().add(lightThemePath);
            }else{
                borderPane.getScene().getStylesheets().clear();
                borderPane.getScene().getStylesheets().add(darkThemePath);
            }
        });
        errMessage.setText("");
        errMessage.prefWidthProperty().bind(borderPane.widthProperty());
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(!(newValue.getText().equals("Admin"))){//change the connected user only if select tab that not Admin
            try {
                Engine.Connect(newValue.getText());
            } catch (Myexception e) {
                errMessage.setText(e.toString());
            }
        }
        });

    }

    public void initEngine(EngineInterface engine) throws IOException {
        this.Engine=engine;
        for(UserDTO dto: engine.getAllUsersDto()){//creates users tabs
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Usertab//UserViewTab.fxml"));
            Parent root=loader.load();
            UserViewTabController controller=loader.getController();
            controller.initUserTab(dto);
            controller.injectMainController(this);
            Tab tab=new Tab(dto.getUsername());
            tab.setContent(root);
            tabPane.getTabs().add(tab);
        }
        //create admin tab
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Admintab/AdminTab.fxml"));
        Parent root=loader.load();
        AdminTabController controller=loader.getController();
        controller.injectMainController(this);
        controller.initData();
        Tab tab=new Tab("Admin");
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }

    public EngineInterface getEngine() {
        return Engine;
    }

    public void updateMessage(String message){ errMessage.setText(message); }
}
