package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import example.*;

public class PlayScreenController {

@FXML
private static Label sclab;

@FXML
private static Label sclabnum;

public static void SetScoreColour(String col){
    sclabnum.setStyle("-fx-text-fill: "+col+";");
    sclab.setStyle("-fx-text-fill: "+col+";");
}

}
