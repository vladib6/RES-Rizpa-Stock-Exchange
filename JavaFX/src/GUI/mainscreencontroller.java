package GUI;

import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.load.Loadxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainscreencontroller  implements Initializable {
    private EngineInterface mainEngine;
    @FXML
    private ImageView lockImageView;

    @FXML
    private Label errMessage;

    @FXML
    private Button button;

    @FXML
    BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Load xml file button
        File lockImage= new File("./JavaFX/src/Resources/pngegg.png");
        Image lkImage= new Image(lockImage.toURI().toString());
        lockImageView.setImage(lkImage);
        button.setGraphic(lockImageView);
        button.setOnMouseClicked(event -> {
            FileChooser fileChooser=new FileChooser();
            File file=fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
            if(file!=null) {
                try {
                    mainEngine = Loadxml.ParseXml(file);
                    //Switch scene
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("AfterLoadScreen.fxml"));
                    Parent userScene= loader.load();
                    Scene newScene=new Scene(userScene);
                    AfterLoadScreenController controller=loader.getController();
                    controller.initEngine(mainEngine);
                    Stage window=((Stage)((Node)event.getSource()).getScene().getWindow());
                    window.setScene(newScene);
                    window.show();
                    //
                } catch (JAXBException | Myexception | FileNotFoundException | StockException e) {
                    errMessage.setVisible(true);
                    errMessage.setText(e.toString());
                } catch (IOException e) {
                    errMessage.setVisible(true);
                    errMessage.setText(e.getMessage());                }
            }
        });

        //Error Label
        errMessage.setText("");
        errMessage.prefWidthProperty().bind(borderPane.widthProperty());

    }



}
