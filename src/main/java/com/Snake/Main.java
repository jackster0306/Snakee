package com.Snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Runs the program
 * Sets up the stage and is used to change screen
 * @author Jack Gribble
 */
public class Main extends Application {
    //Class Variables
    private static Scene m_scene;
    private static Stage m_tstage;

    /**
     * Gets the current scene
     * @return the scene
     */
    public static Scene GetM_scene(){return m_scene;}
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
    public static void SetRoot(String fxml) throws IOException{
        m_scene.setRoot(loadFXML(fxml));
        if(fxml == "PlayScreenMedium"){
            m_tstage.setHeight(375+37);
            m_tstage.setWidth(552+14);
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
        FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("fxml/"+fxml + ".fxml"));
        return fxmlloader.load();
    }
}
