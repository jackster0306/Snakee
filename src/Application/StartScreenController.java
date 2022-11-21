package Application;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class StartScreenController {

    private static boolean bombs = false;

    public static boolean GetToBomb(){
        return bombs;
    }
    Image skyimg = new Image("example/UI-background.png");
    Image cartimg = new Image("example/UI-background2.png");

    static String background;

    public static String GetBackground(){
        return background;
    }

    static String scorecol = "magenta";

    static String snakecol = "green";

    public static String GetScoreCol(){
        return scorecol;
    }

    static int speed = 5;

    static int level = 1;

    public static int GetDiff(){
        return level;
    }

    @FXML
    private ImageView bgimg;

    @FXML
    private Label scorelabel;

    @FXML
    private Label numspeed;

    @FXML
    private TextField speedtf;

    @FXML
    private Label snakelabel;

    @FXML
    private CheckBox checkbomb;

    @FXML
    private Label diff;


    public void SetRedSnake(){
        snakelabel.setStyle("-fx-text-fill: red;");
        snakecol = "red";
    }
    public void SetGreenSnake(){
        snakelabel.setStyle("-fx-text-fill: green;");
        snakecol = "green";
    }
    public void SetMagentaSnake(){
        snakelabel.setStyle("-fx-text-fill: magenta;");
        snakecol = "magenta";
    }
    public void SetYellowSnake(){
        snakelabel.setStyle("-fx-text-fill: yellow;");
        snakecol = "yellow";
    }

    public void SetEasy(){
        diff.setText("Easy");
        level = 1;
    }

    public void SetMedium(){
        diff.setText("Medium");
        level = 2;
    }

    public void SetHard(){
        diff.setText("Hard");
        level = 3;
    }

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

    public static int GetSpeed(){
        return speed;
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
        if(checkbomb.isSelected()){
            bombs = true;
        } else{
            bombs = false;
        }
        if(level == 1){
            StartScreenJFX.setRoot("PlayScreen");
        } else if (level == 2) {
            StartScreenJFX.setRoot("PlayScreenMedium");
        } else if (level == 3) {
            StartScreenJFX.setRoot("PlayScreenHard");
        }
    }
}
