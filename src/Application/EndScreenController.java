package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class EndScreenController {
    @FXML
    private Label endsclabel;
    public void initialize(){
        endsclabel.setText((PlayScreenController.GetScore()));
    }
    public void Restart() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }

    public void Exit(){
        System.exit(0);
    }
}
