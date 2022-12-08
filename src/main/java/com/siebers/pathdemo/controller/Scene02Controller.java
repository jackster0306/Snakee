package main.java.com.siebers.pathdemo.controller;
import com.siebers.pathdemo.Main;
import com.siebers.pathdemo.model.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Scene02Controller {

    @FXML
    private AnchorPane scene02;

    @FXML
    private TextArea txaWelcome;

    @FXML
    void sayBye(ActionEvent event) throws IOException {
        AnchorPane scene01= FXMLLoader.load(Main.class.getResource("fxml/scene01.fxml"));
        scene02.getChildren().removeAll();
        scene02.getChildren().setAll(scene01);
    }

    @FXML
    void initialize() {
        txaWelcome.setText("Welcome "+Data.text);
    }
}
