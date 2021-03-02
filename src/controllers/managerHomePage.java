package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class managerHomePage extends Controller{

    public void managerHomeBackOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/manager.fxml"));
        changeScene(main, event);

    }

    public void submitMembers(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/managerSubmition.fxml"));
        changeScene(main, event);
    }

    public void paySalaries(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/paySalary.fxml"));
        changeScene(main, event);
    }

    public void registerMembers(ActionEvent event) throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("../fxmls/register.fxml"));
        changeScene(register, event);
    }
}
