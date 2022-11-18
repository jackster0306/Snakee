package Application;

import java.awt.desktop.QuitEvent;
import java.io.IOException;

public class EndScreenController {
    public void Restart() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }

    public void Exit(){
        System.exit(0);
    }
}
