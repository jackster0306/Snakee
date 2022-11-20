package Application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class PlayScreenController {
    private static int score = 0;


@FXML
private Label sclab;

@FXML
ImageView foodview;

@FXML
private Label sclabnum;

@FXML
private Pane PlayPaneSky;

int speed;

int direction = 3;

boolean alive = true;

public Timeline timeline;

public Random rand = new Random();

Map<String, Circle> snakebody = new HashMap<>();


public void initialize(){
    sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
    sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
    if(StartScreenController.GetBackground() == "cart"){
        PlayPaneSky.setId("PlayPaneCart");
    }

    StartScreenJFX.scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        KeyPressed(key);
    });
    speed = StartScreenController.GetSpeed();
    timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.03), e -> {
                move();
                try {
                    outofBounds();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (snakehead.intersects(snakehead.sceneToLocal(foodview.localToScene(foodview.getBoundsInLocal())))) {
                    score+=521;
                    Eaten();
                    Circle circ = new Circle();
                    circ.getStyleClass().add("Snake");
                    PlayPaneSky.getChildren().add(circ);
                    snakebody.put(Integer.toString(snakebody.size()),circ);
                }
                sclabnum.setText(Integer.toString(score));
            })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
    Eaten();
    foodview.setVisible(true);
}

public void ToEndScreen() throws IOException {
    alive = false;
    StartScreenJFX.setRoot("EndScreen");
}
@FXML
Arc snakehead;
@FXML
void KeyPressed(KeyEvent e){
    KeyCode code = e.getCode();
    if(code.equals(KeyCode.UP) && direction != 1){
        direction = 0;
        snakehead.setRotate(-90);
    } else if (code.equals(KeyCode.DOWN) && direction != 0) {
        direction = 1;
        snakehead.setRotate(90);
    } else if (code.equals(KeyCode.LEFT)&& direction != 3) {
        direction = 2;
        snakehead.setRotate(-180);
    } else if (code.equals(KeyCode.RIGHT)&& direction != 2) {
        direction = 3;
        snakehead.setRotate(0);
    }
}

public void move(){
    boolean isbody;
    if(snakebody.size()==0)
        isbody = false;
    else isbody = true;
    if(direction == 0){
        snakehead.setLayoutY(snakehead.getLayoutY()-speed);
        if(isbody){
            for(int i =0; i < snakebody.size(); i++){
                snakebody.get(Integer.toString(i)).setLayoutX(snakehead.getLayoutX());
                snakebody.get(Integer.toString(i)).setLayoutY(snakehead.getLayoutY()+(snakehead.getRadiusY()*2*(i+1)));
            }
        }

    } else if (direction == 1) {
        snakehead.setLayoutY(snakehead.getLayoutY()+speed);
        if(isbody){
            for(int i =0; i < snakebody.size(); i++){
                snakebody.get(Integer.toString(i)).setLayoutX(snakehead.getLayoutX());
                snakebody.get(Integer.toString(i)).setLayoutY(snakehead.getLayoutY()-(snakehead.getRadiusY()*2*(i+1)));
            }
        }
    } else if (direction == 2) {
        snakehead.setLayoutX(snakehead.getLayoutX()-speed);
        if(isbody){
            for(int i =0; i < snakebody.size(); i++){
                snakebody.get(Integer.toString(i)).setLayoutY(snakehead.getLayoutY());
                snakebody.get(Integer.toString(i)).setLayoutX(snakehead.getLayoutX()+(snakehead.getRadiusX()*2*(i+1)));
            }
        }
    } else if (direction == 3) {
        snakehead.setLayoutX(snakehead.getLayoutX()+speed);
        if(isbody){
            for(int i =0; i < snakebody.size(); i++){
                snakebody.get(Integer.toString(i)).setLayoutY(snakehead.getLayoutY());
                snakebody.get(Integer.toString(i)).setLayoutX(snakehead.getLayoutX()-(snakehead.getRadiusX()*2*(i+1)));
            }
        }
    }

}

    private void outofBounds() throws IOException {
        /**
         * Creates variables m_MyFrame_xOut and m_MyFrame_yOut
         * Assigns the values that cause the snake to be out of bounds
         * Will be true if the requirements are met
         */
        boolean xOut = (snakehead.getLayoutX() <= 0 || snakehead.getLayoutX() >= 870);
        boolean yOut = (snakehead.getLayoutY() <= 0 || snakehead.getLayoutY() >= 560);
        /**
         * Checks to see if m_MyFrame_xOut or m_MyFrame_yOut are true
         * If they are, snake is no longer visible, game ends
         */
        if (xOut || yOut)
        {
            timeline.stop();
            ToEndScreen();
        }
    }

    public void Eaten(){
        foodview.setLayoutX(rand.nextInt(870-(int)foodview.getFitWidth()));
        foodview.setLayoutY(rand.nextInt(560-(int)foodview.getFitHeight()));
        foodview.setImage(FoodImg.images.get(String.valueOf(new Random().nextInt(17))));
    }
}
