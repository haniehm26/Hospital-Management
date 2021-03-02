package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.*;


public class register {

    @FXML
    JFXTextField yourName;
    @FXML
    JFXTextField yourPassword;
    @FXML
    JFXTextField designation;
    @FXML
    JFXTextField ID;

    private Controller controller = new Controller();
    private Alert alert;
    private boolean check = true;
    private boolean AlertCheck = true;
    private boolean erroreCheck = true;

    public void registerBackOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/managerHomePage.fxml"));
        controller.changeScene(main, event);
    }

    public void registrationOnAction() throws IOException {
        if (yourName.getText().length() != 0 && yourPassword.getText().length() != 0 && yourName.getText().contains(" ") &&
                yourPassword.getText().length() == 10 && designation.getText().length() != 0 && ID.getText().length() != 0 &&
                (designation.getText().equals("Patient")) || designation.getText().equals("Doctor") || designation.getText().equals("Nurse")) {
            erroreCheck = false;
            FileReader fileReader = new FileReader("ID.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name;
            while ((name = bufferedReader.readLine()) != null) {
                if (yourPassword.getText().equals(name.substring(name.indexOf("@") + 1, name.indexOf("*"))) ||
                        ID.getText().equals(name.substring(name.lastIndexOf(" ") + 1))) {
                    check = false;
                }
            }
            if (check) {
                if (designation.getText().equals("Doctor")) {
                    makeFiles("Doctor " + yourName.getText());
                }
                if (designation.getText().equals("Nurse")) {
                    makeFiles("Nurse " + yourName.getText());
                }
                FileOutputStream File = new FileOutputStream("ID.txt", true);
                fileOut(yourName.getText() + "@" + yourPassword.getText() + "* " + "#" + designation.getText() + " *" + "Submission ID : " + ID.getText() + "\n", File);
                successfulRegistration();
            }
        }
        if (!check) {
            alert = new Alert(Alert.AlertType.ERROR, "This person has been added before!");
            alert.show();
            AlertCheck = false;
        }
        if (AlertCheck) {
            alert = new Alert(Alert.AlertType.ERROR, "Please check information!" + "\n" + "Password must be 10 digits!"
                    + "\n" + "Split name and last name by Space !");
            alert.show();
        }
        if(erroreCheck){
            alert = new Alert(Alert.AlertType.ERROR, "Please check information!" + "\n" + "Password must be 10 digits!"
                    + "\n" + "Split name and last name by Space !");
            alert.show();
        }
    }

    public void fileOut(String s, FileOutputStream fileOutputStream) throws IOException {
        byte[] bytes;
        bytes = s.getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private void successfulRegistration() {
        alert = new Alert(Alert.AlertType.INFORMATION, "Registration was successful!");
        alert.show();
        AlertCheck = false;
    }

    private void makeFiles(String name) throws IOException {
        FileOutputStream File = new FileOutputStream(name + ".txt", true);
        fileOut("", File);
    }

    public void clear() {
        yourName.clear();
        yourPassword.clear();
        ID.clear();
        designation.clear();
    }
}