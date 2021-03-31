package GUI;

import com.Engine.EngineInterface;
import com.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoadScreenController implements Initializable {
    private EngineInterface Engine;

    @FXML
    BorderPane borderPane;

    @FXML
    TabPane tabPane;

    @FXML
    Label errMessage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errMessage.setText("");
        errMessage.prefWidthProperty().bind(borderPane.widthProperty());
    }

    public void initEngine(EngineInterface engine) throws IOException {
    this.Engine=engine;
        for(UserDTO dto: engine.getAllUsersDto()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserViewTab.fxml"));
            Parent root=loader.load();
            UserViewTabController controller=loader.getController();
            controller.initUserTab(engine, dto);
            Tab tab=new Tab(dto.getUsername());
            tab.setContent(root);
            tabPane.getTabs().add(tab);
        }
    }
}
