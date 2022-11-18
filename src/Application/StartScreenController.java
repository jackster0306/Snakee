package Application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StartScreenController {


    Image skyimg = new Image("example/UI-background.png");
    Image cartimg = new Image("example/UI-background2.png");

    static String background;

    public static String GetBackground(){
        return background;
    }

    static String scorecol = "magenta";

    public static String GetScoreCol(){
        return scorecol;
    }

    private Stage stage = new Stage();

    int speed = 5;


    @FXML
    private Pane PlayPane;
    @FXML
    private ImageView bgimg;

    @FXML
    private Label scorelabel;

    @FXML
    private Label numspeed;

    @FXML
    private TextField speedtf;



    public void SkyImage(){
        bgimg.setImage(skyimg);
        background = "sky";
    }

    public void CartImage(){
        bgimg.setImage(cartimg);
        background = "cart";
    }

    public void SetSpeed(){
        speed = Integer.parseInt(speedtf.getText());
        numspeed.setText(Integer.toString(speed));
    }

    public void SetRed(){
        scorelabel.setStyle("-fx-text-fill: red;");
        scorecol = "red";
    }
    public void SetGreen(){
        scorelabel.setStyle("-fx-text-fill: green;");
        scorecol = "green";
    }
    public void SetMagenta(){
        scorelabel.setStyle("-fx-text-fill: magenta;");
        scorecol = "magenta";
    }
    public void SetYellow(){
        scorelabel.setStyle("-fx-text-fill: yellow;");
        scorecol = "yellow";
    }

    @FXML
    private void PlayGame() throws IOException {
        StartScreenJFX.setRoot("PlayScreen");
    }
}
