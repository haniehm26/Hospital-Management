package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Patients extends Controller{

    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/login.fxml"));
        changeScene(main, event);
    }

    public void costs(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/HospitalCosts.fxml"));
        changeScene(main, event);
    }

    public void seeReport(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/PatientSeeReport.fxml"));
        changeScene(main, event);
    }
}
