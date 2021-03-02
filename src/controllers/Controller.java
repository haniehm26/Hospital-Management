package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXButton managerBtn;
    @FXML
    private JFXButton noManagerBtn;
    @FXML
    private AnchorPane myHospital;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXButton[] buttons = new JFXButton[]{managerBtn,noManagerBtn};
        for (JFXButton button : buttons) transition(myHospital,button);
    }

    private void transition(AnchorPane anchorPane , JFXButton jfxButton) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1200));
        fadeTransition.setNode(anchorPane);
        fadeTransition.setFromValue(0.2);
        fadeTransition.setToValue(5);
        fadeTransition.play();
        fadeTransition.setNode(jfxButton);
        fadeTransition.setFromValue(0.2);
        fadeTransition.setToValue(5);
        fadeTransition.play();
    }

    public void clickOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/login.fxml"));
        changeScene(main, event);
    }

    public void managerOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/manager.fxml"));
        changeScene(main, event);
    }

    public void changeScene(Parent main, ActionEvent event) {
        Scene mainScene = new Scene(main);
        Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }

}