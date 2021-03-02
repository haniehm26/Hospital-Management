package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login {
    @FXML
    JFXTextField usernameTxt;
    @FXML
    JFXPasswordField passwordTxt;
    @FXML
    JFXTextField designation;

    private Controller controller = new Controller();
    private boolean check = true;
    private Alert alert;

    private boolean checkUserPass() throws IOException {
        boolean check = false;
        FileReader fileReader = new FileReader("ID.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String name;
        while ((name = bufferedReader.readLine()) != null) {
            if (name.substring(0, name.indexOf("*")).equals(usernameTxt.getText() + "@" + passwordTxt.getText()) && name.substring(name.indexOf("#") + 1, name.lastIndexOf("*") - 1).equals(designation.getText()))
                check = true;
        }
        return check && usernameTxt.getText().length() != 0 && passwordTxt.getText().length() != 0;
    }

    public void loginSubmition(ActionEvent event) throws IOException {
        if (checkUserPass() && designation.getText().equals("Doctor")) DoctorPage(event);
        else if (checkUserPass() && designation.getText().equals("Nurse")) NursePage(event);
        else if (checkUserPass() && designation.getText().equals("Patient")) PatientPage(event);
        else submitionError();
    }

    public void forgotYourPassword() throws IOException {
        String name;
        boolean checkAgain = true;
        FileReader fileReader = new FileReader("ID.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((name = bufferedReader.readLine()) != null) {
            checkAgain = false;
            if (name.substring(0, name.indexOf("@")).equals(usernameTxt.getText())) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Your password is : " + name.substring(name.indexOf('@') + 1, name.indexOf('*')));
                alert.show();
                check = true;
                break;
            } else if (!name.substring(0, name.indexOf("@")).equals(usernameTxt.getText())) {
                check = false;
                continue;
            }
            if (usernameTxt.getText().length() == 0 || passwordTxt.getText().length() == 0) {
                checkAgain = true;
                check = true;
            }
        }
        if (checkAgain)
            submitionError();
        else if (!check)
            loginError();
    }

    public void backOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/sample.fxml"));
        controller.changeScene(main, event);
    }


    public void submitionError() {
        alert = new Alert(Alert.AlertType.ERROR, "Check Username ,Password & Designation!");
        alert.show();
        check = true;
    }

    private void loginError() {
        alert = new Alert(Alert.AlertType.ERROR, "You are not registered yet!" + "\n" + "Or Fields are empty!");
        alert.show();
        check = true;
    }

    private void PatientPage(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Patients.fxml"));
        controller.changeScene(main, event);
    }

    private void NursePage(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Nurses.fxml"));
        controller.changeScene(main, event);
    }

    private void DoctorPage(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Doctors.fxml"));
        controller.changeScene(main, event);
    }
}
