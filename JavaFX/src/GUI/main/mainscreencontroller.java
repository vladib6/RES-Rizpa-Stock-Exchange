package GUI.main;

import GUI.AfterLoadScreenController;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.load.Loadxml;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
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
    File file;

    @FXML private ImageView lockImageView;
    @FXML private Label errMessage;
    @FXML private Button button;
    @FXML private BorderPane borderPane;
    @FXML private ScrollPane scrollPane;
    @FXML private ProgressBar progressBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Load xml file button
        File lockImage= new File("./JavaFX/src/Resources/pngegg.png");
        Image lkImage= new Image(lockImage.toURI().toString());
        lockImageView.setImage(lkImage);
        //button
        button.setGraphic(lockImageView);
        button.setOnMouseClicked(event -> {
                    FileChooser fileChooser = new FileChooser();
                    file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

                    if (file != null) {
                        loadTaskfunc();
                    }
                });
        //Error Label
        errMessage.setText("");
        errMessage.prefWidthProperty().bind(borderPane.widthProperty());
        errMessage.prefHeightProperty().bind(borderPane.heightProperty());
        //Progress bar
        progressBar.prefWidthProperty().bind(scrollPane.widthProperty());
        progressBar.setVisible(false);
        //borderpane
        borderPane.prefHeightProperty().bind(scrollPane.heightProperty());
        borderPane.prefWidthProperty().bind(scrollPane.widthProperty());

        //scroll pane
//        borderPane.setPrefSize(400,400);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setPrefViewportWidth(400);
//        scrollPane.setFitToHeight(true);
//        scrollPane.setFitToWidth(true);

    }

    public void loadTaskfunc(){
        Service<EngineInterface> service=new Service<EngineInterface>() {
            @Override
            protected Task<EngineInterface> createTask() {
                return new Task<EngineInterface>() {
                    @Override
                    protected EngineInterface call()  {
                        EngineInterface res=null;
                        progressBar.setVisible(true);
                        try {
                              res = Loadxml.ParseXml(file);
//                            for(int i=1;i<=10;i++){
//                                Thread.sleep(300);
//                                updateProgress(i*100,1000);
//                            }
                            updateMessage("Loading Success");

                        } catch (JAXBException | Myexception | FileNotFoundException | StockException e) {
                            updateMessage(e.toString());
                        }
                        return res;
                    }
                };
            }
        };
        service.setOnSucceeded(event -> {
              errMessage.setText(service.getMessage());
            try {
                if(service.getValue()!=null){
                    SwitchScene(service.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        progressBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

        public void SwitchScene(EngineInterface engine) throws IOException {   //Switch scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../AfterLoadScreen.fxml"));
            Parent userScene = loader.load();
            Scene newScene = new Scene(userScene, 800, 600);
            AfterLoadScreenController controller = loader.getController();
            controller.initEngine(engine);
            Stage window = ((Stage)borderPane.getScene().getWindow());
            window.setScene(newScene);
            window.show();
        }

    }


