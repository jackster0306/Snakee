package Application.Controllers;

import Application.*;
import Application.Obstacles.Bomb;
import Application.Obstacles.Food;
import Application.Obstacles.Wall;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the Play Screen
 * The main class that allows the user to be able to play
 * Moves the snake, spawns food, bombs and walls
 * Refreshes the screen
 * @author Jack Gribble
 */
public class PlayScreenController {
    //FXML Variables
    @FXML
    private Label sclab;
    @FXML
    private Label sclabnum;
    @FXML
    private Pane PlayPaneSky;
    @FXML
    private Rectangle snakehead;
    @FXML
    private Label NameLabel;

    //Class Variables
    private static int m_score = 0;
    private Food m_food;
    private int m_direction = 3;
    private static Timeline m_timeline;
    private Timeline m_bombspawntl;
    private Timeline m_bombdonetl;
    private static Map<Integer, Rectangle> m_snakebody;
    private Map<Integer, Double> m_xpositions;
    private Map<Integer, Double> m_ypositions;
    private int m_gameticks = 0;
    private static boolean m_newfood = false;
    private static double m_xbound, m_ybound;
    private boolean m_isbombs;
    private Bomb m_bomb, m_bomb1, m_bomb2;
    private int m_bombspawn, m_difficulty, SPEED;
    private Wall m_wall;
    private static boolean m_hit = false;
    private boolean m_intersects;
    private double m_time = 0.3;
    private Image m_wallimg;
    private int m_playlevel;
    Image m_snakeheadimg;
    Snake m_snake;

    //Getters
    public static String GetScore() {
        return Integer.toString(m_score);
    }
    public static Double GetXBound(){
        return m_xbound;
    }
    public static Double GetYBound(){
        return m_ybound;
    }

    //Setters
    public static void SetXBound(double x){m_xbound = x;}
    public static void SetYBound(double y){m_ybound = y;}
    public static void SetM_newfood(boolean b){
        m_newfood = b;
    }
    public static void SetScore(int i){m_score = i;}
    public static void SetHit(boolean b){m_hit = b;}

    /**
     * Sets up the Play Screen when it gets loaded
     * Contains everything to do when the Play Screen is loaded
     * Sets up all the components in the screen to have the default values
     * Calls the Timelines that are used to make the game run
     */
    public void initialize(){
        m_hit = false;
        m_snakebody = new HashMap<>();
        m_ypositions = new HashMap<>();
        m_xpositions = new HashMap<>();
        m_playlevel = 1;
        m_time = 0.03;
        Image foodimg = new Image(Theme.GetFoodImg());
        m_wallimg = new Image(Theme.GetWallImg());
        Image bombimg = new Image(Theme.GetBombImg());
        PlayPaneSky.setId(Theme.GetBackground());
        NameLabel.setText("Player Name: "+StartScreenController.GetPlayerName());
        m_snakeheadimg = new Image(Theme.GetSnakeHImg());
        snakehead.setFill(new ImagePattern(m_snakeheadimg));
        m_gameticks = 0;
        m_score = 0;
        m_isbombs = StartScreenController.GetToBomb();
        m_difficulty = StartScreenController.GetDiff();
        if(m_difficulty == 1){
            setVariables(870,560,14);
        } else {
            setVariables(552, 375, 9);
        }
        m_bomb = new Bomb(PlayPaneSky, bombimg);
        m_bomb1 = new Bomb(PlayPaneSky, bombimg);
        m_bomb2 = new Bomb(PlayPaneSky, bombimg);
        m_wall = new Wall(PlayPaneSky, m_wallimg);
        sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        Main.GetM_scene().addEventHandler(KeyEvent.KEY_PRESSED, this::KeyPressed);
        SPEED = 5;
        m_food = new Food(PlayPaneSky, foodimg);
        m_food.GetM_food().setLayoutX(400);
        m_food.GetM_food().setLayoutY(325);
        m_snake = new Snake(PlayPaneSky, snakehead, m_xbound, m_ybound);
        mainTimeline();

        bombTimelines();
        wallTimeline();
    }

    /**
     * The main timeline of the game which 'updates' the screen every x seconds
     * Used to move the snake and see if the snake has hit anything or gone out of bounds
     * Used to keep the score updated
     */
    private void mainTimeline(){
        m_timeline = new Timeline(new KeyFrame(Duration.seconds(m_time), e -> {
            move();
            if(m_score >= (1000*m_playlevel)*m_playlevel){
                if(m_playlevel < 3){
                    SwitchLevel();
                }

            }
            if(m_newfood){
                m_snake.addSnakeBody(m_snakebody);
            }
            for(int i = 0; i < m_snakebody.size(); i++){
                double x = m_xpositions.get(m_gameticks -((SPEED)*(i+1))-1);
                double y = m_ypositions.get(m_gameticks -((SPEED)*(i+1))-1);
                m_snake.moveSnakeBody(m_snakebody.get(i),x, y );
            }
            try {
                m_snake.outofBounds();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            m_intersects = CheckBounds(m_food.GetM_food());
            if (m_intersects) {
                new MusicPlayer(Theme.GetFoodSound(), false);
                m_score +=521;
                m_food.MoveFood(snakehead);
                m_newfood = true;
            }
            m_intersects = CheckBounds(m_wall.GetM_wall());
            if (m_intersects) {
                m_wall.removeWall();
                try {
                    m_snake.RemoveSnakeBody(m_hit, m_snakebody);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            searchSnakeBody();
            try {
                checkBomb(m_bomb.GetM_bomb());
                checkBomb(m_bomb1.GetM_bomb());
                checkBomb(m_bomb2.GetM_bomb());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sclabnum.setText(Integer.toString(m_score));
            if(m_direction == 0){
                setPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 1) {
                setPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 2) {
                setPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 3) {
                setPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            }
            m_gameticks++;
        }));
        m_timeline.setCycleCount(Timeline.INDEFINITE);
        m_timeline.play();
    }

    /**
     * The timelines for the bombs
     * If bombs are activated, it will spawn bombs after x seconds, and then they will despawn after 15 seconds then repeat the process again
     */
    private void bombTimelines(){
        m_bombspawntl = new Timeline(new KeyFrame(Duration.seconds(m_bombspawn), e -> {
            if(m_isbombs){
                m_bomb.BombSpawn(snakehead);
                if(m_difficulty == 2)
                    m_bomb1.BombSpawn(snakehead);
                else if(m_difficulty == 3){
                    m_bomb1.BombSpawn(snakehead);
                    m_bomb2.BombSpawn(snakehead);
                }
                m_bombdonetl.play();
                m_bombspawntl.stop();
            }
        })
        );
        m_bombspawntl.setCycleCount(Timeline.INDEFINITE);
        m_bombspawntl.play();
        m_bombdonetl = new Timeline(new KeyFrame(Duration.seconds(15), e -> {
            m_bomb.BombEnd();
            m_bomb1.BombEnd();
            m_bomb2.BombEnd();
            m_bombspawntl.play();
            m_bombdonetl.stop();
        })
        );
        m_bombdonetl.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Checks to see if the snakehead has collided with any of the snake body parts
     * If so, the game ends and the user is sent to the End Screen
     */
    private void searchSnakeBody(){
        for(int i = 1; i < m_snakebody.size(); i++){
            m_intersects = CheckBounds(m_snakebody.get(i));
            if(m_intersects){
                try {
                    toEndScreen();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /**
     * The timeline for the wall
     * Spawns the wall after 8 seconds, and will move it or spawn a new one every 8 seconds
     * If the current wall has been hit, it creates a new one
     * If the current wall hasn't been hit, it moves it
     */
    private void wallTimeline(){
        Timeline m_walltl = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            if (m_hit) {
                m_wall = new Wall(PlayPaneSky, m_wallimg);
            } else
                m_wall.moveWall(snakehead);
            m_hit = false;
        }));
        m_walltl.setCycleCount(Timeline.INDEFINITE);
        m_walltl.play();
    }

    /**
     * Adds the x and y co-ordinates of the snakehead at the time it is called to the X and Y Maps
     * @param x the current x co-ordinate of the snakehead
     * @param y the current y co-ordinate of the snakehead
     */
    private void setPositions(double x, double y){
        m_xpositions.put(m_gameticks, x);
        m_ypositions.put(m_gameticks, y);
    }

    /**
     * Sets the variables for how often bombs are spawned and the bounds of the screen
     * @param x the x bound of the screen
     * @param y the y bound of the screen
     * @param b the number of seconds the bombs will spawn after
     */
    private void setVariables(double x, double y, int b){
        m_bombspawn = b;
        m_xbound = x;
        m_ybound = y;
    }

    /**
     * Checks to see if the snakehead has collided with the provided bomb
     * If it has, the game is ended and the End Screen is loaded
     * @param thebomb the bomb to be checked
     * @throws IOException
     */
    private void checkBomb(ImageView thebomb) throws IOException {
        if(thebomb.isVisible()){
            m_intersects = CheckBounds(thebomb);
            if(m_intersects){
                toEndScreen();
            }
        }
    }

    /**
     * Checks if the snakehead has collided with the given Node
     * @param n the node to check
     * @return a boolean whether the snake head has hit the given node
     */
    public boolean CheckBounds(Node n){
        Bounds bound = n.getBoundsInLocal();
        Bounds snakebound = n.localToScene(bound);
        return snakehead.intersects(snakehead.sceneToLocal(snakebound));
    }


    //Transition. AnchorPane opacity to 1, Stop timeline, Fade in Fade out, sequential, change speed, start timeline


    /**
     * Loads the End Screen
     * Called when the game is over
     * @throws IOException
     */
    public static void toEndScreen() throws IOException {
        new MusicPlayer("src/Resources/Music/Gameoveraudio.mp3", false);
        m_timeline.stop();
        Main.SetRoot("EndScreen");
    }

    /**
     * Changes the direction of the snake and rotates the head
     * Called when a key is pressed
     * Checks which key is pressed and changes direction and rotates the head accordingly
     * @param e the event (key pressed)
     */
    @FXML
    void KeyPressed(KeyEvent e){
        KeyCode code = e.getCode();
        if((code.equals(KeyCode.UP) || code.equals(KeyCode.W))&& m_direction != 1){
            m_direction = 0;
            snakehead.setRotate(-90);
        } else if ((code.equals(KeyCode.DOWN) || code.equals(KeyCode.S)) && m_direction != 0) {
            m_direction = 1;
            snakehead.setRotate(90);
        } else if ((code.equals(KeyCode.LEFT) || code.equals(KeyCode.A))&& m_direction != 3) {
            m_direction = 2;
            snakehead.setRotate(-180);
        } else if ((code.equals(KeyCode.RIGHT) || code.equals(KeyCode.D))&& m_direction != 2) {
            m_direction = 3;
            snakehead.setRotate(0);
        }
    }

    /**
     * Moves the snakehead
     * Checks which direction the snakehead is facing and changes the x and y co-ordinates accordingly
     */
    private void move(){
        if(m_direction == 0){
            snakehead.setLayoutY(snakehead.getLayoutY()- SPEED);
        } else if (m_direction == 1) {
            snakehead.setLayoutY(snakehead.getLayoutY()+ SPEED);
        } else if (m_direction == 2) {
            snakehead.setLayoutX(snakehead.getLayoutX()- SPEED);
        } else if (m_direction == 3) {
            snakehead.setLayoutX(snakehead.getLayoutX()+ SPEED);
        }
    }

    /**
     * Switches to the next level
     * Will increase the speed of the snake
     */
    private void SwitchLevel(){
        m_timeline.stop();
        if(m_playlevel == 1){
            m_time = 0.0175;
        } else{
            m_time = 0.01;
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Level "+m_playlevel);
        a.setContentText("Score: "+sclabnum.getText()+"\nOnto Level "+(m_playlevel+1));
        a.setOnHidden(evt -> mainTimeline());
        a.show();
        m_playlevel++;
    }
}