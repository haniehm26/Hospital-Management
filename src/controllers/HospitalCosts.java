package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HospitalCosts extends paySalary {
    @FXML
    JFXTextField ID;
    @FXML
    JFXTextField doctorName;
    @FXML
    JFXTextField nurseName;

    private Controller controller = new Controller();
    private register register = new register();
    private Alert alert;
    private long budgetS = 0;
    private boolean check = true;
    private boolean showCheck = true;
    private boolean costCheck = true;
    private boolean fileCheck = true;
    private boolean reportCheck = true;


    public void backOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/Patients.fxml"));
        controller.changeScene(main, event);
    }


    public void payCost() throws IOException {
        if (showCheck) {
            alert = new Alert(Alert.AlertType.ERROR, "Click Show Costs first!");
            alert.show();
        }
        StringBuilder newFile = new StringBuilder();
        StringBuilder newFileId = new StringBuilder();
        StringBuilder newFileDoc = new StringBuilder();
        StringBuilder newFileNur = new StringBuilder();
        if (!showCheck) {
            FileReader fileReader = new FileReader("Report.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name;
            String temp;
            while ((name = bufferedReader.readLine()) != null) {
                if (ID.getText().equals(name.substring(0, name.indexOf(" "))) && doctorCheck() && nurseCheck() && ("Patient's Doctor : " + doctorName.getText()).equals(name.substring(name.indexOf("!") + 2, name.indexOf("@") - 1))) {
                    temp = name.substring(name.indexOf(")") + 2, name.indexOf("_"));
                    temp = temp.substring(temp.indexOf(":") + 2, temp.lastIndexOf(" "));
                    if (costCheck && doctorCheck() && nurseCheck()) {
                        budget += (budgetS += Long.parseLong(temp));
                        costCheck = false;
                        fileCheck = false;
                        name = "";
                    } else {
                        alert = new Alert(Alert.AlertType.INFORMATION, temp + " Paid successfully!" + "\n" + "You are dismissed!");
                        alert.show();
                    }
                }
                newFile.append(!name.equals("") ? name + "\n" : "");
            }
            if (!fileCheck) {
                fileReader = new FileReader("ID.txt");
                bufferedReader = new BufferedReader(fileReader);
                String id;
                while ((id = bufferedReader.readLine()) != null) {
                    if (ID.getText().equals(id.substring(id.indexOf("@") + 1, id.indexOf("*")))) {
                        id = "";
                    }
                    newFileId.append(!id.equals("") ? id + "\n" : "");
                }
                if (doctorCheck()) {
                    fileReader = new FileReader("Doctor " + doctorName.getText() + ".txt");
                    bufferedReader = new BufferedReader(fileReader);
                    String doc;
                    while ((doc = bufferedReader.readLine()) != null) {
                        if (ID.getText().equals(doc.substring(doc.indexOf("@") + 1))) {
                            doc = "";
                        }
                        newFileDoc.append(!doc.equals("") ? doc + "\n" : "");
                    }
                }
                if (nurseCheck()) {
                    fileReader = new FileReader("Nurse " + nurseName.getText() + ".txt");
                    bufferedReader = new BufferedReader(fileReader);
                    String nur;
                    while ((nur = bufferedReader.readLine()) != null) {
                        if (ID.getText().equals(nur.substring(nur.indexOf("@") + 1))) {
                            nur = "";
                        }
                        newFileNur.append(!nur.equals("") ? nur + "\n" : "");
                    }
                }
            }

            FileOutputStream File1 = new FileOutputStream("Salary.txt");
            register.fileOut(budget + "\n", File1);
        }
        if (!fileCheck) {
            FileOutputStream File = new FileOutputStream("Report.txt");
            register.fileOut(newFile.toString(), File);
            File = new FileOutputStream("ID.txt");
            register.fileOut(newFileId.toString(), File);
            File = new FileOutputStream("Doctor " + doctorName.getText() + ".txt");
            register.fileOut(newFileDoc.toString(), File);
            File = new FileOutputStream("Nurse " + nurseName.getText() + ".txt");
            register.fileOut(newFileNur.toString(), File);
        }
        if (!costCheck) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Paid successfully!");
            alert.show();
        }
    }

    public void showCosts() throws IOException {
        if (costCheck) {
            if (ID.getText().length() == 0 || nurseName.getText().length() == 0 || doctorName.getText().length() == 0) {
                alert = new Alert(Alert.AlertType.ERROR, "Field is empty!");
                alert.show();
                return;
            }
            FileReader fileReader = new FileReader("Report.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name;
            while ((name = bufferedReader.readLine()) != null) {
                reportCheck = false;
                if (name.substring(0, name.indexOf(" ")).equals(ID.getText()) ) {
                    alert = new Alert(Alert.AlertType.INFORMATION, name.substring(name.indexOf(")") + 2, name.indexOf("_")) + "\n" +
                            name.substring(name.indexOf("_") + 2, name.indexOf(".")));
                    alert.show();
                    check = true;
                    showCheck = false;
                    break;
                } else if (!(name.substring(0, name.indexOf(" ")).equals(ID.getText()))&& doctorCheck() && nurseCheck() && ("Patient's Doctor : " + doctorName.getText()).equals(name.substring(name.indexOf("!") + 2, name.indexOf("@") - 1))) {
                    check = false;
                }
            }
            if (!check) {
                alert = new Alert(Alert.AlertType.ERROR, "This person doesn't exists!"+"\n"+"Or Doctor's name , Nurse name is wrong!");
                alert.show();
            }
        }
        if (!costCheck) {
            alert = new Alert(Alert.AlertType.INFORMATION, "You are dismissed!" + "\n" + "There is no cost!");
            alert.show();
        }
        if (reportCheck) {
            alert = new Alert(Alert.AlertType.ERROR, "You can't be dismissed!" + "\n" + "There is no report!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

}
