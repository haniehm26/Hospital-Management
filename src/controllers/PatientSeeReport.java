package controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class PatientSeeReport {
    @FXML
    JFXListView listView;
    @FXML
    JFXTextField nameLastName;
    @FXML
    JFXCheckBox doctor;
    @FXML
    JFXCheckBox nurse;
    @FXML
    JFXCheckBox patient;

    private Controller controller = new Controller();
    private ArrayList<String> strings = new ArrayList<>();
    private boolean checkShow = true;
    private boolean check = true;
    private boolean Dcheck = true;
    private boolean Ncheck = true;
    private boolean Pcheck = true;
    private int counter = 0;

    private void addReport() throws IOException {
        FileReader fileReader = new FileReader("Report.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String name;
        String temp;
        while ((name = bufferedReader.readLine()) != null) {
            if (checkShow && nameLastName.getText().equals(name.substring(0, name.indexOf(" ")))) {
                temp = name.substring(name.indexOf(String.valueOf(BigInteger.valueOf(Long.parseLong(nameLastName.getText())))) + 3, name.lastIndexOf("."));
                strings.add(temp.substring(temp.indexOf("!") + 2, temp.indexOf("@")) + "\n" +
                        temp.substring(temp.indexOf("@") + 2, temp.indexOf("#")) + "\n" +
                        temp.substring(temp.indexOf("#") + 2, temp.indexOf("$")) + "\n" +
                        temp.substring(temp.indexOf("$") + 2, temp.indexOf("%")) + "\n" +
                        temp.substring(temp.indexOf("%") + 2, temp.indexOf("^")) + "\n" +
                        temp.substring(temp.indexOf("^") + 2, temp.indexOf("&")) + "\n" +
                        temp.substring(temp.indexOf("&") + 2, temp.indexOf("*")) + "\n" +
                        temp.substring(temp.indexOf("*") + 2, temp.indexOf("(")) + "\n" +
                        temp.substring(temp.indexOf("(") + 2, temp.indexOf(")")) + "\n" +
                        temp.substring(temp.indexOf(")") + 2, temp.indexOf("_")) + "\n" +
                        temp.substring(temp.indexOf("_") + 2) + "\n");
                break;
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        if (!Pcheck) {
            Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Patients.fxml"));
            controller.changeScene(main, event);
        } else if (!Ncheck) {
            Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Nurses.fxml"));
            controller.changeScene(main, event);
        } else if (!Dcheck) {
            Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Doctors.fxml"));
            controller.changeScene(main, event);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Submit what you are!");
            alert.show();
        }
    }

    public void showReport() throws IOException {
        FileReader fileReader = new FileReader("Report.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String name;
        while ((name = bufferedReader.readLine()) != null) {
            if (name.substring(0, name.indexOf(" ")).equals(nameLastName.getText()) && nameLastName.getText().length() != 0) {
                check = false;
                break;
            }
        }
        if (!check) {
            counter++;
            addReport();
            listView.setItems(FXCollections.observableList(strings));
            checkShow = false;
            if (!checkShow && counter >= 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report showed successfully!");
                alert.show();
            }
            check = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This person doesn't exist!");
            alert.show();
        }
    }

    public void Clear() {
        nameLastName.clear();
        strings.clear();
        check = false;
        checkShow = true;
        listView.setItems(FXCollections.observableList(strings));
    }

    public void doctorA() {
        Dcheck = false;
    }

    public void nurseA() {
        Ncheck = false;
    }

    public void patientA() {
        Pcheck = false;
    }
}