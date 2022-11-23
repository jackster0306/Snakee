package Application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayScreenController {
    private static int score = 0;


    @FXML
    private Label sclab;

    @FXML
    private Label sclabnum;

    @FXML
    private Pane PlayPaneSky;

    @FXML
    private ImageView bomb;

    @FXML
    private ImageView bomb1;

    @FXML
    private ImageView bomb2;

    Food food;

    int speed;

    int direction = 3;

    boolean alive = true;

    public Timeline timeline;

    public Timeline bombspawntl;

    public Timeline bombdonetl;

    public Random rand = new Random();

    Map<String, Circle> snakebody = new HashMap<>();

    Map<Integer, Double> xpositions = new HashMap<>();
    Map<Integer, Double> ypositions = new HashMap<>();

    int gameticks = 0;

    public boolean newfood = false;

    static double xbound;
    static double ybound;

    public boolean isbombs;

    int bombspawn;

    int difficulty;

    public static String GetScore() {
        return Integer.toString(score);
    }

    public static Double GetXBound(){
        return xbound;
    }

    public static Double GetYBound(){
        return ybound;
    }



    public void initialize(){
        score = 0;
        isbombs = StartScreenController.GetToBomb();
        difficulty = StartScreenController.GetDiff();
        if(difficulty == 1){
            bombspawn = 14;
            xbound = 870;
            ybound = 560;
        } else if (difficulty == 2) {
            bombspawn = 9;
            xbound = 550;
            ybound = 400;
        } else if (difficulty == 3) {
            bombspawn = 5;
            xbound = 420;
            ybound = 280;
        }
        sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        if(StartScreenController.GetBackground() == "cart"){
            PlayPaneSky.setId("PlayPaneCart");
        }
        snakehead.setStyle("-fx-fill: "+StartScreenController.snakecol+";");

        StartScreenJFX.scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyPressed(key);
        });
        speed = StartScreenController.GetSpeed();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.03), e -> {
                    move();
                    if(newfood){
                        Circle circ = new Circle(13);
                        circ.setStyle("-fx-fill: "+StartScreenController.snakecol+";");
                        PlayPaneSky.getChildren().add(circ);
                        snakebody.put(Integer.toString(snakebody.size()),circ);
                        newfood = false;
                    }
                    for(int i =0; i < snakebody.size(); i++){
                        moveSnakeBody(snakebody.get(Integer.toString(i)), i+1);
                    }
                    try {
                        outofBounds();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Bounds foodbounds = food.food.localToScene(food.food.getBoundsInLocal());
                    Bounds snakebounds = snakehead.sceneToLocal(foodbounds);
                    if (snakehead.intersects(snakebounds)) {
                        score+=521;
                        food.MoveFood();
                        newfood = true;
                    }

                    for(int i =1; i < snakebody.size(); i++){
                        Bounds sb = snakebody.get(Integer.toString(i)).getBoundsInLocal();
                        Bounds sb2 = snakebody.get(Integer.toString(i)).localToScene(sb);
                        if(snakehead.intersects(snakehead.sceneToLocal(sb2))){
                            try {
                                ToEndScreen();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    if(isbombs && bomb.isVisible()){
                        try {
                            CheckBomb(bomb);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if(direction == 2){
                            try {
                                CheckBomb(bomb1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if(direction == 3){
                            try {
                                CheckBomb(bomb1);
                                CheckBomb(bomb2);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    sclabnum.setText(Integer.toString(score));
                    if(direction == 0){
                        xpositions.put(gameticks, snakehead.getLayoutX());
                        ypositions.put(gameticks, snakehead.getLayoutY()+3);
                    } else if (direction == 1) {
                        xpositions.put(gameticks, snakehead.getLayoutX());
                        ypositions.put(gameticks, snakehead.getLayoutY()-4);
                    } else if (direction == 2) {
                        xpositions.put(gameticks, snakehead.getLayoutX()-1);
                        ypositions.put(gameticks, snakehead.getLayoutY());
                    } else if (direction == 3) {
                        xpositions.put(gameticks, snakehead.getLayoutX()-4);
                        ypositions.put(gameticks, snakehead.getLayoutY());
                    }
                    gameticks++;
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        food = new Food(rand.nextInt((int)xbound),rand.nextInt((int)ybound), PlayPaneSky);

        if(isbombs){
            bombspawntl = new Timeline(
                    new KeyFrame(Duration.seconds(bombspawn), e -> {
                        if(!bomb.isVisible()){
                            Bomb.BombSpawn(bomb);
                            if(difficulty == 2){
                                Bomb.BombSpawn(bomb1);
                            } else if(difficulty == 3){
                                Bomb.BombSpawn(bomb1);
                                Bomb.BombSpawn(bomb2);
                            }
                            bombdonetl.play();
                            bombspawntl.stop();
                        }
                    })
            );
            bombspawntl.setCycleCount(Timeline.INDEFINITE);
            bombspawntl.play();

            bombdonetl = new Timeline(
                    new KeyFrame(Duration.seconds(15), e -> {
                        if(bomb.isVisible()){
                            BombEnd(bomb);
                            if(difficulty == 2){
                                BombEnd(bomb1);
                            } else if(difficulty == 3){
                                BombEnd(bomb1);
                                BombEnd(bomb2);
                            }
                            bombspawntl.play();
                            bombdonetl.stop();
                        }
                    })
            );
            bombdonetl.setCycleCount(Timeline.INDEFINITE);
        }
    }



    public void CheckBomb(ImageView thebomb) throws IOException {
        Bounds bombbound = thebomb.localToScene(thebomb.getBoundsInLocal());
        Bounds snakebound = snakehead.sceneToLocal(bombbound);
        if(snakehead.intersects(snakebound)){
            ToEndScreen();
        }
    }

    public void BombEnd(ImageView img){
        if(img.isVisible()){
            img.setVisible(false);
        }
    }


    public void moveSnakeBody(Circle bodypart, int num){
        double x = xpositions.get(gameticks-((speed)*num));
        double y = ypositions.get(gameticks-((speed)*num));
        bodypart.setLayoutX(x);
        bodypart.setLayoutY(y);
    }
    public void ToEndScreen() throws IOException {
        alive = false;
        timeline.stop();
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
        if(direction == 0){
            snakehead.setLayoutY(snakehead.getLayoutY()-speed);
        } else if (direction == 1) {
            snakehead.setLayoutY(snakehead.getLayoutY()+speed);
        } else if (direction == 2) {
            snakehead.setLayoutX(snakehead.getLayoutX()-speed);
        } else if (direction == 3) {
            snakehead.setLayoutX(snakehead.getLayoutX()+speed);
        }

    }
    private void outofBounds() throws IOException {
        boolean xOut = (snakehead.getLayoutX() <= 0 || snakehead.getLayoutX() >= xbound);
        boolean yOut = (snakehead.getLayoutY() <= 0 || snakehead.getLayoutY() >= ybound);

        if (xOut || yOut)
        {
            ToEndScreen();
        }
    }

}
