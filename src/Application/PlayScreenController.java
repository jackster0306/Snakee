package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import example.*;
import javafx.scene.layout.Pane;

public class PlayScreenController {
    private static int score = 0;

@FXML
private Label sclab;

@FXML
private Label sclabnum;

@FXML
private Pane PlayPaneSky;

public void initialize(){
    sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
    sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
    if(StartScreenController.GetBackground() == "cart"){
        PlayPaneSky.setId("PlayPaneCart");
    }
    //When snake is setup, I will also set the speed as entered in the start screen.
}


}
