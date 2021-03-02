package controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DoctorReport {
    @FXML
    JFXTextArea comment;
    @FXML
    JFXTextField doctorName;
    @FXML
    JFXTextField FirstName;
    @FXML
    JFXTextField LastName;
    @FXML
    JFXTextField Age;
    @FXML
    JFXTextField Weight;
    @FXML
    JFXTextField Sex;
    @FXML
    JFXTextField Height;
    @FXML
    JFXTextField RoomNo;
    @FXML
    JFXTextField NationalID;
    @FXML
    JFXCheckBox Electrocardiogram;
    @FXML
    JFXCheckBox Xray;
    @FXML
    JFXCheckBox Serum;
    @FXML
    JFXCheckBox Endoscopia;
    @FXML
    JFXCheckBox Tablets;
    @FXML
    JFXCheckBox Ampule;
    @FXML
    JFXCheckBox Tests;

    private Controller controller = new Controller();
    private register register = new register();
    private boolean check = true;
    private boolean checkW = true;
    private long cost = 0;
    private ArrayList<String> strings = new ArrayList<>();

    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Doctors.fxml"));
        controller.changeScene(main, event);
    }

    public void submit() throws IOException {
        if (comment.getText().length() == 0 || doctorName.getText().length() == 0 || FirstName.getText().length() == 0 || LastName.getText().length() == 0 || Age.getText().length() == 0 ||
                Weight.getText().length() == 0 || Height.getText().length() == 0 || Sex.getText().length() == 0 || RoomNo.getText().length() == 0 || NationalID.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields are empty!");
            alert.show();
        } else {
            if (doctorCheck()) {
                FileReader fileReader = new FileReader("Doctor " + doctorName.getText() + ".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String name;
                while ((name = bufferedReader.readLine()) != null) {
                    if (name.substring(0, name.indexOf(" ")).equals(FirstName.getText()) && name.substring(name.indexOf(" ") + 1, name.indexOf("@")).equals(LastName.getText())) {
                        checkW = false;
                        break;
                    }
                }
            }
            if (!checkW) {
                if (check) {
                    FileOutputStream File = new FileOutputStream("Report.txt", true);
                    register.fileOut(NationalID.getText() + " !/Patient's Doctor : " + doctorName.getText() + " @/Patient's First Name : " + FirstName.getText() +
                            " #/Patient's Last Name : " + LastName.getText() + " $/Patient's Age : " + Age.getText() +
                            " %/Patient's Weight : " + Weight.getText() + " ^/Patient's Sex : " + Sex.getText() + " &/Patient's Height : "
                            + Height.getText() + " */Patient's Room Number : " + RoomNo.getText() + " (/Comments : " + comment.getText() + " )/Patient's costs : " + costs() + " _/Patient's costs details : " + strings + "." + "\n", File);
                    check = false;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report submitted successfully!");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This person doesn't exists!" + "\n" + "Or fields information has been entered wrong!");
                alert.show();
            }
        }
    }

    private boolean doctorCheck() throws IOException {
        boolean check = false;
        FileReader fileReader = new FileReader("ID.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String name;

        while ((name = bufferedReader.readLine()) != null) {
            if (name.substring(name.indexOf("#") + 1, name.lastIndexOf("*")).contains("Doctor")) {
                if (name.substring(0, name.indexOf("@")).equals(doctorName.getText())) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public void Electrocardiogram() {
        cost += 100_000;
        strings.add("Electrocardiogram : 100,000" + "    ");
    }

    public void Xray() {
        cost += 200_000;
        strings.add("Xray : 200,000" + "    ");
    }

    public void Serum() {
        cost += 10_000;
        strings.add("Serum : 10,000" + "    ");
    }

    public void Endoscopia() {
        cost += 150_000;
        strings.add("Endoscopia : 150,000" + "    ");
    }

    public void Tablets() {
        cost += 5_000;
        strings.add("Tablets : 5,000" + "    ");
    }

    public void Ampule() {
        cost += 7_000;
        strings.add("Ampule : 7,000" + "    ");
    }

    public void Tests() {
        cost += 270_000;
        strings.add("Tests : 270,000" + "    ");
    }

    private long costs() {
        return cost;
    }
}
