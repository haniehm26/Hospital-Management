package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;


public class manager {

    @FXML
    private JFXTextField managerUsername;
    @FXML
    private JFXPasswordField managerPassword;

    private login login = new login();
    private Controller controller = new Controller();


    public void managerSubmit(javafx.event.ActionEvent event) throws IOException {
        if (checkUserPass()) {
            managerSubmitOnAction(event);
        } else
            login.submitionError();
    }

    private void managerSubmitOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/managerHomePage.fxml"));
        controller.changeScene(main, event);
    }

    public void managerBackOnAction(javafx.event.ActionEvent event) throws IOException {
        login.backOnAction(event);
    }

    private boolean checkUserPass() {
        return managerUsername.getText().length() != 0 && managerPassword.getText().length() != 0 &&
                managerPassword.getText().equals(managerUsername.getText());
    }
}