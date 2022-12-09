package com.Snake.Models;

import com.Snake.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneSwitch {
    public SceneSwitch(AnchorPane pane, String filename) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Main.class.getResource(filename));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(nextAnchorPane);
    }
}
