package controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class paySalary extends DoctorNurseCheck implements Initializable {
    @FXML
    JFXTextArea budgets;
    private Controller controller = new Controller();
    private Alert alert;
    protected Long budget;
    private boolean ErrorCheck = true;
    private register register = new register();

    public void managerPaySalaryOnAction(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/managerHomePage.fxml"));
        controller.changeScene(main, event);
    }

    {
        try {
            FileReader fileReader1 = new FileReader("Salary.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            String name1;
            while ((name1 = bufferedReader1.readLine()) != null) {
                budget = Long.parseLong(name1);
            }
        } catch (IOException e) {
            ErrorCheck = false;
            e.printStackTrace();
        }
        if (!ErrorCheck) {
            alert = new Alert(Alert.AlertType.INFORMATION, "No patient paid costs!");
            alert.show();
        }
    }

    public void payDoctorSalary() throws IOException {
        budget -= 2_000_000;
        FileOutputStream File = new FileOutputStream("Salary.txt");
        register.fileOut(budget + "\n", File);
        budgets.setText(String.valueOf(budget));
        alert = new Alert(Alert.AlertType.INFORMATION, "2,000,000 paid successfully!");
        alert.show();
    }

    public void payNurseSalary() throws IOException {
        budget -= 1_500_000;
        FileOutputStream File = new FileOutputStream("Salary.txt");
        register.fileOut(budget + "\n", File);
        budgets.setText(String.valueOf(budget));
        alert = new Alert(Alert.AlertType.INFORMATION, "1,500,000 paid successfully!");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        budgets.setText(String.valueOf(budget));
    }

    @Override
    protected boolean nurseCheck() throws IOException {
        return false;
    }

    @Override
    protected boolean doctorCheck() throws IOException {
        return false;
    }
}
