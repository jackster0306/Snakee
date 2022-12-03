package Application;

import java.io.IOException;

/**
 * Controls the Rules Screen
 */
public class RulesController {
    /**
     * Loads the Start Screen
     * Called when the 'back' button is pressed
     * @throws IOException
     */
    public void Back() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }
}
