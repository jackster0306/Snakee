package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class StartScreenJFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Create the Pane and all Details
        Pane SSroot =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScreen.fxml")));

        // Create the Scene
        Scene scene = new Scene(SSroot);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("Snakee");
        // Display the Stage
        stage.show();
    }
}
