package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

public class DoctorAddPatient extends DoctorNurseCheck {
    @FXML
    JFXTextField patientName;
    @FXML
    JFXTextField patientLastName;
    @FXML
    JFXTextField doctorName;
    @FXML
    JFXListView listView;
    private boolean loopCheck = true;
    private boolean equalCheck = true;
    private boolean check = true;
    private ArrayList<String> strings = new ArrayList<>();
    private NurseAddPatient nurseAddPatient = new NurseAddPatient();
    private register register = new register();
    private Controller controller = new Controller();

    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Doctors.fxml"));
        controller.changeScene(main, event);
    }

    public void addPatient() throws IOException {
        if (doctorName.getText().length() != 0) {
            if (check) {
                if (doctorCheck()) {
                    FileReader fileReader1 = new FileReader("Doctor " + doctorName.getText() + ".txt");
                    BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
                    String name1;
                    while ((name1 = bufferedReader1.readLine()) != null) {
                        strings.add(name1);
                    }
                    check = false;
                }
            }
            if (strings.size() == 3) {
                showPatientList();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "3 Patients added successfully!" + "\n" +
                        "Maybe you have added them before!");
                alert.show();
            } else if (strings.size() <= 3) {
                if (loopCheck) {
                    FileReader fileReader = new FileReader("ID.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String name;
                    while ((name = bufferedReader.readLine()) != null) {
                        if (nurseAddPatient.substringCheck(name, patientName, patientLastName)) {
                            if (nurseAddPatient.toStringCheck(strings, patientName, patientLastName)) {
                                nurseAddPatient.checkSame();
                                equalCheck = true;
                                break;
                            }
                            if (name.substring(name.indexOf("#") + 1, name.lastIndexOf("*") - 1).equals("Patient")) {
                                strings.add(patientName.getText() + " " + patientLastName.getText() + " " + name.substring(name.indexOf("@") + 1, name.indexOf("*")) + "\n");
                                FileOutputStream File = new FileOutputStream("Doctor " + doctorName.getText() + ".txt", true);
                                register.fileOut(patientName.getText() + " " + patientLastName.getText() + "@" + name.substring(name.indexOf("@") + 1, name.indexOf("*")) + "\n", File);
                                clear();
                                equalCheck = true;
                                showPatientList();
                                break;
                            }
                        }
                        if (nurseAddPatient.zeroCheck(patientName.getText().length(), patientLastName.getText().length(), doctorName.getText().length())) {
                            nurseAddPatient.checkNameCheckLastName();
                            equalCheck = true;
                            break;
                        }
                        if (strings.size() == 3) {
                            nurseAddPatient.cantAddMore();
                            equalCheck = true;
                            loopCheck = false;
                            check = false;
                            break;
                        } else if (!nurseAddPatient.substringCheck(name, patientName, patientLastName)) {
                            equalCheck = false;
                            continue;
                        }
                    }
                    if (!equalCheck) nurseAddPatient.equalCheck();
                } else nurseAddPatient.cantAddMore();
            } else nurseAddPatient.cantAddMore();
        } else if (doctorName.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Check fields!");
            alert.show();
        }
    }

    private void clear() {
        patientName.clear();
        patientLastName.clear();
    }

    private void showPatientList() {
        listView.setItems(FXCollections.observableList(strings));
    }

    @Override
    protected boolean nurseCheck() throws IOException {
        return false;
    }

    @Override
    protected boolean doctorCheck() throws IOException {
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
}