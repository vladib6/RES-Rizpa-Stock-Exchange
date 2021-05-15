package GUI.ThemeAnimation;

import GUI.Afterloadscreen.AfterLoadScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemeAnimation implements Initializable {
    private AfterLoadScreenController mainController;
    private  boolean animation;
    @FXML private AnchorPane anchorPane;
    @FXML private ToggleGroup themeToggleGroup;
    @FXML private ToggleButton classicMode;
    @FXML private ToggleButton lightMode;
    @FXML private ToggleButton darkMode;
    @FXML private CheckBox animationBox;

    final String classicThemePath= getClass().getResource("../main/mainscreenstyle.css").toExternalForm();
    final String lightThemePath= getClass().getResource("../main/light-mode.css").toExternalForm();
    final String darkThemePath= getClass().getResource("../main/dark-mode.css").toExternalForm();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.animation=newValue;
            mainController.setAnimationState(animation);

        });
        themeToggleGroup=new ToggleGroup();
        themeToggleGroup.getToggles().addAll(classicMode,lightMode,darkMode);
    }

    public void Init(String theme,boolean animation,AfterLoadScreenController mainController){
        this.mainController=mainController;

            if(theme.equals("classic")){
                classicMode.setSelected(true);
            }else if(theme.equals("light")){
                lightMode.setSelected(true);
            }else{
                darkMode.setSelected(true);
            }
            this.animation=animation;
            animationBox.setSelected(animation);

        themeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(classicMode.isSelected()){
                anchorPane.getScene().getStylesheets().clear();
                anchorPane.getScene().getStylesheets().add(classicThemePath);
                mainController.setTheme("classic");
            }else if(lightMode.isSelected()){
                anchorPane.getScene().getStylesheets().clear();
                anchorPane.getScene().getStylesheets().add(lightThemePath);
                mainController.setTheme("light");
            }else{
                anchorPane.getScene().getStylesheets().clear();
                anchorPane.getScene().getStylesheets().add(darkThemePath);
                mainController.setTheme("dark");

            }
        });
    }




}
