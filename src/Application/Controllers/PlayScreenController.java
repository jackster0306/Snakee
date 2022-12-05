package Application.Controllers;

import Application.*;
import Application.Obstacles.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
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
import java.util.Random;

/**
 * Controls the Play Screen
 * The main class that allows the user to be able to play
 * Moves the snake, spawns food, bombs and walls
 * Refreshes the screen
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
    private boolean m_alive = true;
    private Timeline m_timeline, m_bombspawntl, m_bombdonetl;
    private Random m_rand = new Random();
    private Map<String, Rectangle> m_snakebody = new HashMap<>();
    private Map<Integer, Double> m_xpositions = new HashMap<>();
    private Map<Integer, Double> m_ypositions = new HashMap<>();
    private int m_gameticks = 0;
    private boolean m_newfood = false;
    private static double m_xbound, m_ybound;
    private boolean m_isbombs;
    private Bomb m_bomb, m_bomb1, m_bomb2;
    private int m_bombspawn, m_difficulty, m_speed;
    private Wall m_wall;
    private Timeline m_walltl;
    private boolean m_hit = false;
    private boolean m_intersects;
    private double m_time = 0.3;
    private Image m_wallimg;
    private Image snakeheadimg;
    private Image snakebodyimg;

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

    /**
     * Sets up the Play Screen when it gets loaded
     * Contains everything to do when the Play Screen is loaded
     * Sets up all the components in the screen to have the default values
     * Calls the Timelines that are used to make the game run
     */
    public void initialize(){
        Image foodimg = new Image(Theme.GetFoodImg());
        m_wallimg = new Image(Theme.GetWallImg());
        Image bombimg = new Image(Theme.GetBombImg());
        PlayPaneSky.setId(Theme.GetBackground());
        NameLabel.setText("Player Name: "+StartScreenController.GetPlayerName());
        m_time = StartScreenController.GetSpeed();
        snakeheadimg = new Image(Theme.GetSnakeHImg());
        snakehead.setFill(new ImagePattern(snakeheadimg));
        snakebodyimg = new Image(Theme.GetSnakeBImg());
        m_gameticks = 0;
        m_score = 0;
        m_isbombs = StartScreenController.GetToBomb();
        m_difficulty = StartScreenController.GetDiff();
        if(m_difficulty == 1){
            SetVariables(870,560,14);
        } else if (m_difficulty == 2) {
            SetVariables(550,400,9);
        } else if (m_difficulty == 3) {
            SetVariables(420,280,5);
        }
        m_bomb = new Bomb(PlayPaneSky, bombimg);
        m_bomb1 = new Bomb(PlayPaneSky, bombimg);
        m_bomb2 = new Bomb(PlayPaneSky, bombimg);
        m_wall = new Wall(PlayPaneSky, m_wallimg);
        sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        StartScreenJFX.GetM_scene().addEventHandler(KeyEvent.KEY_PRESSED, this::KeyPressed);
        m_speed = 5;
        MainTimeline();
        m_food = new Food(PlayPaneSky, foodimg);
        BombTimelines();
        WallTimeline();
    }

    /**
     * The main timeline of the game which 'updates' the screen every x seconds
     * Used to move the snake and see if the snake has hit anything or gone out of bounds
     * Used to keep the score updated
     */
    public void MainTimeline(){
        m_timeline = new Timeline(new KeyFrame(Duration.seconds(m_time), e -> {
            move();
            if(m_newfood){
                AddSnakeBody();
            }
            for(int i = 0; i < m_snakebody.size(); i++){
                moveSnakeBody(m_snakebody.get(Integer.toString(i)), i+1);
            }
            try {
                outofBounds();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            m_intersects = CheckBounds(m_food.GetM_food());
            if (m_intersects) {
                new MusicPlayer(Theme.GetFoodSound(), false);
                m_score +=521;
                m_food.MoveFood();
                m_newfood = true;
            }
            m_intersects = CheckBounds(m_wall.GetM_wall());
            if (m_intersects) {
                m_wall.removeWall();
                try {
                    RemoveSnakeBody();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            SearchSnakeBody();
            try {
                CheckBomb(m_bomb.GetM_bomb());
                CheckBomb(m_bomb1.GetM_bomb());
                CheckBomb(m_bomb2.GetM_bomb());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sclabnum.setText(Integer.toString(m_score));
            if(m_direction == 0){
                SetPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 1) {
                SetPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 2) {
                SetPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            } else if (m_direction == 3) {
                SetPositions(snakehead.getLayoutX(),snakehead.getLayoutY());
            }
            m_gameticks++;
        }));
        m_timeline.setCycleCount(Timeline.INDEFINITE);
        m_timeline.play();
    }

    /**
     * The timelines for the bombs
     * If bombs are activated, it will spawn bombs after x seconds and then they will despawn after 15 seconds then repeat the process again
     */
    public void BombTimelines(){
        m_bombspawntl = new Timeline(new KeyFrame(Duration.seconds(m_bombspawn), e -> {
            if(m_isbombs){
                m_bomb.BombSpawn();
                if(m_difficulty == 2)
                    m_bomb1.BombSpawn();
                else if(m_difficulty == 3){
                    m_bomb1.BombSpawn();
                    m_bomb2.BombSpawn();
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
    public void SearchSnakeBody(){
        for(int i = 1; i < m_snakebody.size(); i++){
            m_intersects = CheckBounds(m_snakebody.get(Integer.toString(i)));
            if(m_intersects){
                try {
                    ToEndScreen();
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
    public void WallTimeline(){
        m_walltl = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            if(m_hit){
                m_wall = new Wall(PlayPaneSky, m_wallimg);
            } else
                m_wall.moveWall();
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
    public void SetPositions(double x, double y){
        m_xpositions.put(m_gameticks, x);
        m_ypositions.put(m_gameticks, y);
    }

    /**
     * Sets the variables for how often bombs are spawned and the bounds of the screen
     * @param x the x bound of the screen
     * @param y the y bound of the screen
     * @param b the number of seconds the bombs will spawn after
     */
    public void SetVariables(double x, double y, int b){
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
    public void CheckBomb(ImageView thebomb) throws IOException {
        if(thebomb.isVisible()){
            m_intersects = CheckBounds(thebomb);
            if(m_intersects){
                ToEndScreen();
            }
        }
    }

    /**
     * Removes the tail of the snake
     * Called when the snakehead hits a wall
     * @throws IOException
     */
    public void RemoveSnakeBody() throws IOException {
        if(!m_hit){
            new MusicPlayer(Theme.GetWallSound(), false);
            if(m_snakebody.size() == 0)
                ToEndScreen();
            PlayPaneSky.getChildren().remove(m_snakebody.get(Integer.toString(m_snakebody.size()-1)));
            m_snakebody.remove(Integer.toString(m_snakebody.size()-1));
            m_score -= 521;
        }
        m_hit = true;
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

    /**
     * Adds a body part to the snake
     * Called when the snake 'eats' a piece of food
     */
    public void AddSnakeBody(){
        Rectangle rect = new Rectangle(26,26);
        rect.setFill(new ImagePattern(snakebodyimg));
        PlayPaneSky.getChildren().add(rect);
        m_snakebody.put(Integer.toString(m_snakebody.size()),rect);
        m_newfood = false;
    }

    /**
     * Moves the given body part of the snake
     * Sets the x and y co-ordinates of the body part
     * @param bodypart the body part to move
     * @param num the body part number
     */
    public void moveSnakeBody(Rectangle bodypart, int num){
        double x = m_xpositions.get(m_gameticks -((m_speed)*num)-1);
        double y = m_ypositions.get(m_gameticks -((m_speed)*num)-1);
        bodypart.setLayoutX(x);
        bodypart.setLayoutY(y);
    }

    /**
     * Loads the End Screen
     * Called when the game is over
     * @throws IOException
     */
    public void ToEndScreen() throws IOException {
        new MusicPlayer("src/Resources/Music/Gameoveraudio.mp3", false);
        m_alive = false;
        m_timeline.stop();
        StartScreenJFX.setRoot("EndScreen");
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
    public void move(){
        if(m_direction == 0){
            snakehead.setLayoutY(snakehead.getLayoutY()- m_speed);
        } else if (m_direction == 1) {
            snakehead.setLayoutY(snakehead.getLayoutY()+ m_speed);
        } else if (m_direction == 2) {
            snakehead.setLayoutX(snakehead.getLayoutX()- m_speed);
        } else if (m_direction == 3) {
            snakehead.setLayoutX(snakehead.getLayoutX()+ m_speed);
        }
    }

    /**
     * Checks to see if the snakehead is out of bounds
     * If it is the game is ended and the End Screen is loaded
     * @throws IOException
     */
    private void outofBounds() throws IOException {
        boolean xOut = (snakehead.getLayoutX() <= 0 || snakehead.getLayoutX() >= m_xbound);
        boolean yOut = (snakehead.getLayoutY() <= 0 || snakehead.getLayoutY() >= m_ybound);

        if (xOut || yOut)
        {
            ToEndScreen();
        }
    }
}