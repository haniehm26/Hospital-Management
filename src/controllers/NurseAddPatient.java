package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
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

public class NurseAddPatient extends DoctorNurseCheck {
    @FXML
    JFXTextField PatientName;
    @FXML
    JFXTextField PatientLastName;
    @FXML
    JFXTextField nurseName;
    @FXML
    JFXListView ListView;
    private boolean loopCheck = true;
    private boolean equalCheck = true;
    private boolean check = true;
    private ArrayList<String> strings = new ArrayList<>();
    private Controller controller = new Controller();
    private register register = new register();
    private Alert alert;

    public void addPatient() throws IOException {
        if (nurseName.getText().length() != 0) {
            if (check) {
                if (nurseCheck()) {
                    FileReader fileReader1 = new FileReader("Nurse " + nurseName.getText() + ".txt");
                    BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
                    String name1;
                    while ((name1 = bufferedReader1.readLine()) != null) {
                        strings.add(name1);
                    }
                    check = false;
                }
            }
            if (strings.size() == 5) {
                showPatientList();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "5 Patients added successfully!" + "\n" +
                        "Maybe you have added them before!");
                alert.show();
            } else if (strings.size() <= 5) {
                if (loopCheck) {
                    FileReader fileReader = new FileReader("ID.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String name;
                    while ((name = bufferedReader.readLine()) != null) {
                        if (zeroCheck(PatientName.getText().length(), PatientLastName.getText().length(), nurseName.getText().length())) {
                            checkNameCheckLastName();
                            equalCheck = true;
                            break;
                        }
                        if (substringCheck(name, PatientName, PatientLastName)) {
                            if (toStringCheck(strings, PatientName, PatientLastName)) {
                                checkSame();
                                equalCheck = true;
                                break;
                            }
                            if (name.substring(name.indexOf("#") + 1, name.lastIndexOf("*") - 1).equals("Patient")) {
                                strings.add(PatientName.getText() + " " + PatientLastName.getText() + " " + name.substring(name.indexOf("@") + 1, name.indexOf("*")) + "\n");
                                FileOutputStream File = new FileOutputStream("Nurse " + nurseName.getText() + ".txt", true);
                                register.fileOut(PatientName.getText() + " " + PatientLastName.getText() + "@" + name.substring(name.indexOf("@") + 1, name.indexOf("*")) + "\n", File);
                                clear();
                                equalCheck = true;
                                showPatientList();
                                break;
                            }
                        }
                        if (strings.size() == 5) {
                            cantAddMore();
                            equalCheck = true;
                            loopCheck = false;
                            break;
                        } else if (!substringCheck(name, PatientName, PatientLastName)) {
                            equalCheck = false;
                            continue;
                        }
                    }
                    if (!equalCheck) equalCheck();
                } else cantAddMore();
            } else cantAddMore();
        } else if (nurseName.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Check fields!");
            alert.show();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Nurses.fxml"));
        controller.changeScene(main, event);
    }

    private void clear() {
        PatientName.clear();
        PatientLastName.clear();
    }

    private void showPatientList() {
        ListView.setItems(FXCollections.observableList(strings));
    }

    public void equalCheck() {
        alert = new Alert(Alert.AlertType.ERROR, "This person hasn't been registered yet!" +
                "\n" + "It can't be added!");
        alert.show();
    }

    public void checkNameCheckLastName() {
        alert = new Alert(Alert.AlertType.ERROR, "Check fields!");
        alert.show();
    }

    public void checkSame() {
        alert = new Alert(Alert.AlertType.ERROR, "This person has been added before!");
        alert.show();
    }

    public boolean substringCheck(String name, JFXTextField fName, JFXTextField lName) {
        return name.substring(0, name.indexOf(" ")).equals(fName.getText()) && name.substring(name.indexOf(" ") +
                1, name.indexOf("@")).equals(lName.getText());
    }

    public boolean toStringCheck(ArrayList arrayList, JFXTextField fName, JFXTextField lName) {
        return arrayList.toString().contains(fName.getText() + " " + lName.getText());
    }

    public boolean zeroCheck(int fName, int lName, int check) {
        return (fName == 0 || lName == 0 || check == 0);
    }

    public void cantAddMore() {
        alert = new Alert(Alert.AlertType.ERROR, "You can't add more!");
        alert.show();
    }

    @Override
    protected boolean nurseCheck() throws IOException {
        boolean check = false;
        FileReader fileReader = new FileReader("ID.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String name;

        while ((name = bufferedReader.readLine()) != null) {
            if (name.substring(name.indexOf("#") + 1, name.lastIndexOf("*")).contains("Nurse")) {
                if (name.substring(0, name.indexOf("@")).equals(nurseName.getText())) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    protected boolean doctorCheck() throws IOException {
        return false;
    }
}
