package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Doctors extends Controller {


    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/login.fxml"));
        changeScene(main, event);
    }

    public void addPatients(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/DoctorAddPatient.fxml"));
        changeScene(main, event);

    }

    public void reportRegistration(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/DoctorReport.fxml"));
        changeScene(main, event);
    }

    public void seeReport(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/PatientSeeReport.fxml"));
        changeScene(main, event);
    }
}
