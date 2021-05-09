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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoadScreenController implements Initializable {
    private EngineInterface Engine;

    @FXML private BorderPane borderPane;
    @FXML private TabPane tabPane;
    @FXML private Label errMessage;
    @FXML private ScrollPane scrollPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

       // borderpane
        borderPane.prefHeightProperty().bind(scrollPane.heightProperty());
        borderPane.prefWidthProperty().bind(scrollPane.widthProperty());
       // scroll pane
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(115, 150);
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
