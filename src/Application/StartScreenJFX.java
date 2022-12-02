package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenJFX extends Application {
    static Scene m_scene;
    public static Stage m_tstage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        m_tstage = stage;
        m_scene = new Scene(loadFXML("StartScreen"));
        m_tstage.setScene(m_scene);
        m_tstage.show();
    }


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

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(StartScreenJFX.class.getResource(fxml + ".fxml"));
        return fxmlloader.load();
    }
}
