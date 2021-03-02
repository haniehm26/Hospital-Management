package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Nurses extends Controller{

    public void addPatients(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/NurseAddPatient.fxml"));
        changeScene(main, event);
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/login.fxml"));
        changeScene(main, event);
    }

    public void showReport(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/PatientSeeReport.fxml"));
        changeScene(main, event);
    }
}
