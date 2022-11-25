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
import java.util.*;

public class PlayScreenController {
    @FXML
    private Label sclab;

    @FXML
    private Label sclabnum;

    @FXML
    private Pane PlayPaneSky;

    @FXML
    Arc snakehead;

    private static int score = 0;
    Food food;

    int direction = 3;

    boolean alive = true;

    public Timeline timeline, bombspawntl, bombdonetl;

    public Random rand = new Random();

    Map<String, Circle> snakebody = new HashMap<>();

    Map<Integer, Double> xpositions = new HashMap<>();
    Map<Integer, Double> ypositions = new HashMap<>();

    int gameticks = 0;

    public boolean newfood = false;

    static double xbound, ybound;

    public boolean isbombs;

    Bomb bomb, bomb1, bomb2;

    int bombspawn, difficulty, speed;

    Wall wall;

    boolean wallhit = false;


    public static String GetScore() {
        return Integer.toString(score);
    }

    public static Double GetXBound(){
        return xbound;
    }

    public static Double GetYBound(){
        return ybound;
    }

    int wallticks;

    Timeline walltl;

    int hit = 0;

    public void initialize(){
        wallticks = 0;
        gameticks = 0;
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
        bomb = new Bomb(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
        bomb1 = new Bomb(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
        bomb2 = new Bomb(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
        wall = new Wall(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
        sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        if(StartScreenController.GetBackground() == "cart"){
            PlayPaneSky.setId("PlayPaneCart");
        }
        snakehead.setStyle("-fx-fill: "+StartScreenController.snakecol+";");

        StartScreenJFX.scene.addEventHandler(KeyEvent.KEY_PRESSED, this::KeyPressed);
        speed = StartScreenController.GetSpeed();
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.03), e -> {
                    move();
                    if(newfood){
                        AddSnakeBody();
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

                    Bounds wallbound = wall.wall.localToScene(wall.wall.getBoundsInLocal());
                    Bounds snakebound = snakehead.sceneToLocal(wallbound);
                    if (snakehead.intersects(snakebound)) {
                        wall.removeWall();
                        try {
                            RemoveSnakeBody();
                            wallhit = true;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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
                        try {
                            CheckBomb(bomb.bomb);
                            CheckBomb(bomb1.bomb);
                            CheckBomb(bomb2.bomb);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    sclabnum.setText(Integer.toString(score));
                    if(direction == 0){
                        SetPositions(snakehead.getLayoutX()-1,snakehead.getLayoutY()+2);
                    } else if (direction == 1) {
                        SetPositions(snakehead.getLayoutX()-2,snakehead.getLayoutY()-3);
                    } else if (direction == 2) {
                        SetPositions(snakehead.getLayoutX()-1,snakehead.getLayoutY());
                    } else if (direction == 3) {
                        SetPositions(snakehead.getLayoutX()-4,snakehead.getLayoutY());
                    }
                    gameticks++;
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        food = new Food(rand.nextInt((int)xbound),rand.nextInt((int)ybound), PlayPaneSky);

        bombspawntl = new Timeline(
                new KeyFrame(Duration.seconds(bombspawn), e -> {
                    if(isbombs){
                        bomb.BombSpawn();
                        if(difficulty == 2)
                            bomb1.BombSpawn();
                        else if(difficulty == 3){
                            bomb1.BombSpawn();
                            bomb2.BombSpawn();
                        }

                    }
                })
            );
            bombspawntl.setCycleCount(Timeline.INDEFINITE);
            bombspawntl.play();

            bombdonetl = new Timeline(
                    new KeyFrame(Duration.seconds(15), e -> {
                        bomb.BombEnd();
                        bomb1.BombEnd();
                        bomb2.BombEnd();
                        bombspawntl.play();
                        bombdonetl.stop();
                    })
            );
            bombdonetl.setCycleCount(Timeline.INDEFINITE);

        walltl = new Timeline(
                new KeyFrame(Duration.seconds(20), e -> {
                    if(hit != 0){
                        wall = new Wall(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
                    } else
                        wall.moveWall();
                    hit = 0;
                    wallticks++;
                })
        );
        walltl.setCycleCount(Timeline.INDEFINITE);
        walltl.play();
        }

    public void SetPositions(double x, double y){
        xpositions.put(gameticks, x);
        ypositions.put(gameticks, y);
    }

    public void CheckBomb(ImageView thebomb) throws IOException {
        if(thebomb.isVisible()){
            Bounds bombbound = thebomb.localToScene(thebomb.getBoundsInLocal());
            Bounds snakebound = snakehead.sceneToLocal(bombbound);
            if(snakehead.intersects(snakebound)){
                ToEndScreen();
            }
        }
    }

    public void RemoveSnakeBody() throws IOException {
        if(hit == 0){
            if(snakebody.size() == 0)
                ToEndScreen();
            PlayPaneSky.getChildren().remove(snakebody.get(Integer.toString(snakebody.size()-1)));
            snakebody.remove(Integer.toString(snakebody.size()-1));
            score -= 521;
        }
        hit = 1;
    }

    public void AddSnakeBody(){
        Circle circ = new Circle(13);
        circ.setStyle("-fx-fill: "+StartScreenController.snakecol+";");
        PlayPaneSky.getChildren().add(circ);
        snakebody.put(Integer.toString(snakebody.size()),circ);
        newfood = false;
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