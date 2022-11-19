package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import example.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

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
    StartScreenJFX.scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        KeyPressed(key);
    });
    //When snake is setup, I will also set the speed as entered in the start screen.
}

public void ToEndScreen() throws IOException {
    StartScreenJFX.setRoot("EndScreen");
}
@FXML
void KeyPressed(KeyEvent e){
    KeyCode code = e.getCode();
    if(code.equals(KeyCode.UP)){
        System.out.println("UP PRESSED");
    } else if (code.equals(KeyCode.DOWN)) {
        System.out.println("DOWN PRESSED");
    } else if (code.equals(KeyCode.LEFT)) {
        System.out.println("LEFT PRESSED");
    }
    else if (code.equals(KeyCode.RIGHT)) {
        System.out.println("RIGHT PRESSED");
    }
}


}
