package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenJFX extends Application {
    static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage tstage;
    @Override
    public void start(Stage stage) throws IOException {
        tstage = stage;
        scene = new Scene(loadFXML("StartScreen"));
        tstage.setScene(scene);
        tstage.show();
    }


    static void setRoot(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
        if(fxml == "PlayScreenMedium"){
            tstage.setHeight(420+37);
            tstage.setWidth(600+14);
        } else if (fxml == "PlayScreenHard") {
            tstage.setHeight(280+37);
            tstage.setWidth(420+14);
        } else if (fxml == "PlayScreen") {
            tstage.setHeight(560+37);
            tstage.setWidth(870+14);
        } else if (fxml == "StartScreen"){
            tstage.setHeight(560+37);
            tstage.setWidth(870+14);
        } else{
            tstage.setHeight(406+37);
            tstage.setWidth(400+14);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(StartScreenJFX.class.getResource(fxml + ".fxml"));
        return fxmlloader.load();
    }
}
