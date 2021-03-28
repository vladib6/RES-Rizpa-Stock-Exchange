package GUI;

import com.Engine.EngineInterface;
import com.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoadScreenController implements Initializable {
    private EngineInterface Engine;


    @FXML
    BorderPane borderPane;

    @FXML
    TabPane  tabPane;

    @FXML
    Label errMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            errMessage.setText("");
    }

    public void initEngine(EngineInterface engine){
       this.Engine=engine;
        for(UserDTO dto: engine.getAllUsersDto()){
            Tab tab=new Tab(dto.getUsername(),new Label("Show all planes available"));
            tabPane.getTabs().add(tab);
        }    }
}
