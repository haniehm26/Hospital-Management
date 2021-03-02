package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

public class managerSubmition {
    @FXML
    JFXListView listView;

    private Controller controller = new Controller();
    private ArrayList<String> names = new ArrayList<>();
    private boolean checkAdd = true;

    public void backOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("../fxmls/managerHomePage.fxml"));
        controller.changeScene(main, event);
    }

    public void Add() throws IOException {
        if (checkAdd) {
            FileReader fileReader = new FileReader("ID.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name;
            while ((name = bufferedReader.readLine()) != null) {
                names.add(name.substring(0, name.indexOf("@")) + " | " + name.substring(name.lastIndexOf("*") + 1) + "\n");
                listView.setItems(FXCollections.observableList(names));
                checkAdd = false;
            }
            if (names.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is no member for showing!");
                alert.show();
            }
        } else
            successfulAlert();
    }

    private void successfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Submission is successful!");
        alert.show();
    }
}
