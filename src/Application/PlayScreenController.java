package Application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
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

    boolean hit = false;

    boolean intersects;

    public void initialize(){
        new MusicPlayer("src/Resources/frogger.mp3");
        wallticks = 0;
        gameticks = 0;
        score = 0;
        isbombs = StartScreenController.GetToBomb();
        difficulty = StartScreenController.GetDiff();
        if(difficulty == 1){
            SetVariables(870,560,14);
        } else if (difficulty == 2) {
            SetVariables(550,400,9);
        } else if (difficulty == 3) {
            SetVariables(420,280,5);
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
        MainTimeline();
        food = new Food(rand.nextInt((int)xbound),rand.nextInt((int)ybound), PlayPaneSky);
        BombTimelines();
        WallTimeline();
        }

    public void MainTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.03), e -> {
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
            intersects = CheckBounds(food.food);
            if (intersects) {
                score+=521;
                food.MoveFood();
                newfood = true;
            }
            intersects = CheckBounds(wall.wall);
            if (intersects) {
                wall.removeWall();
                try {
                    RemoveSnakeBody();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            SearchSnakeBody();
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
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void BombTimelines(){
        bombspawntl = new Timeline(new KeyFrame(Duration.seconds(bombspawn), e -> {
            if(isbombs){
                bomb.BombSpawn();
                if(difficulty == 2)
                    bomb1.BombSpawn();
                else if(difficulty == 3){
                    bomb1.BombSpawn();
                    bomb2.BombSpawn();
                }
                bombdonetl.play();
                bombspawntl.stop();
            }
        })
        );
        bombspawntl.setCycleCount(Timeline.INDEFINITE);
        bombspawntl.play();
        bombdonetl = new Timeline(new KeyFrame(Duration.seconds(15), e -> {
            bomb.BombEnd();
            bomb1.BombEnd();
            bomb2.BombEnd();
            bombspawntl.play();
            bombdonetl.stop();
        })
        );
        bombdonetl.setCycleCount(Timeline.INDEFINITE);
    }

    public void SearchSnakeBody(){
        for(int i =1; i < snakebody.size(); i++){
            intersects = CheckBounds(snakebody.get(Integer.toString(i)));
            if(intersects){
                try {
                    ToEndScreen();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void WallTimeline(){
        walltl = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            if(hit){
                wall = new Wall(rand.nextInt((int) xbound), rand.nextInt((int) ybound), PlayPaneSky);
            } else
                wall.moveWall();
            hit = false;
            wallticks++;
        }));
        walltl.setCycleCount(Timeline.INDEFINITE);
        walltl.play();
    }
    public void SetPositions(double x, double y){
        xpositions.put(gameticks, x);
        ypositions.put(gameticks, y);
    }

    public void SetVariables(double x, double y, int b){
        bombspawn = b;
        xbound = x;
        ybound = y;
    }

    public void CheckBomb(ImageView thebomb) throws IOException {
        if(thebomb.isVisible()){
            intersects = CheckBounds(thebomb);
            if(intersects){
                ToEndScreen();
            }
        }
    }

    public void RemoveSnakeBody() throws IOException {
        if(!hit){
            if(snakebody.size() == 0)
                ToEndScreen();
            PlayPaneSky.getChildren().remove(snakebody.get(Integer.toString(snakebody.size()-1)));
            snakebody.remove(Integer.toString(snakebody.size()-1));
            score -= 521;
        }
        hit = true;
    }

    public boolean CheckBounds(Node n){
        Bounds bound = n.getBoundsInLocal();
        Bounds snakebound = n.localToScene(bound);
        return snakehead.intersects(snakehead.sceneToLocal(snakebound));
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
        if((code.equals(KeyCode.UP) || code.equals(KeyCode.W))&& direction != 1){
            direction = 0;
            snakehead.setRotate(-90);
        } else if ((code.equals(KeyCode.DOWN) || code.equals(KeyCode.S)) && direction != 0) {
            direction = 1;
            snakehead.setRotate(90);
        } else if ((code.equals(KeyCode.LEFT) || code.equals(KeyCode.A))&& direction != 3) {
            direction = 2;
            snakehead.setRotate(-180);
        } else if ((code.equals(KeyCode.RIGHT) || code.equals(KeyCode.D))&& direction != 2) {
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