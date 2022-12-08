package com.Snake.controller;

import com.Snake.Main;
import com.Snake.model.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Scene01Controller {

    @FXML
    private AnchorPane scene01;

    @FXML
    private TextField txtName;

    @FXML
    void moveToNextScene(ActionEvent event) throws IOException {
        Data.text=txtName.getText();
        AnchorPane scene02= FXMLLoader.load(Main.class.getResource("fxml/scene02.fxml"));
        scene01.getChildren().removeAll();
        scene01.getChildren().setAll(scene02);
    }

    @FXML
    void quitProgram(ActionEvent event) {
        System.out.println("Bye!");
        System.exit(0);
    }

    @FXML
    void initialize() {
        txtName.setText(Data.text);
    }
}

