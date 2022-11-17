package Application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;

public class StartScreenController {


    Image skyimg = new Image("example/UI-background.png");
    Image cartimg = new Image("example/UI-background2.png");

    String scorecol = "magenta";

    int speed = 5;



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
    }

    public void CartImage(){
        bgimg.setImage(cartimg);
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

    public void PlayGame(){
        PlayScreenController.SetScoreColour(scorecol);
    }
}
