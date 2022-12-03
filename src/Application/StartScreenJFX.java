package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Runs the program
 * Sets up the stage and is used to change screen
 */
public class StartScreenJFX extends Application {
    //Class Variables
    static Scene m_scene;
    public static Stage m_tstage;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets up and starts the stage when the program is run
     * @param stage the stage to setup and run
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        m_tstage = stage;
        m_scene = new Scene(loadFXML("StartScreen"));
        m_tstage.setScene(m_scene);
        m_tstage.show();
    }

    /**
     * Changes the root of the Scene
     * Switches scene to the string provided
     * @param fxml the name of the fxml file to switch the root to
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException{
        m_scene.setRoot(loadFXML(fxml));
        if(fxml == "PlayScreenMedium"){
            m_tstage.setHeight(420+37);
            m_tstage.setWidth(600+14);
        } else if (fxml == "PlayScreenHard") {
            m_tstage.setHeight(280+37);
            m_tstage.setWidth(420+14);
        } else if (fxml == "PlayScreen") {
            m_tstage.setHeight(560+37);
            m_tstage.setWidth(870+14);
        } else if (fxml == "LeaderboardScreen"){
            m_tstage.setHeight(467+37);
            m_tstage.setWidth(472+14);
        } else{
            m_tstage.setHeight(560+37);
            m_tstage.setWidth(870+14);
        }
    }

    /**
     * Loads the fxml file
     * @param fxml the name of the fxml file
     * @return the loaded screen
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(StartScreenJFX.class.getResource(fxml + ".fxml"));
        return fxmlloader.load();
    }
}
