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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayScreenController {
    @FXML
    private Label sclab;

    @FXML
    private Label sclabnum;

    @FXML
    private Pane PlayPaneSky;

    @FXML
    Arc snakehead;

    @FXML
    private Label NameLabel;

    private static int m_score = 0;
    Food m_food;

    int m_direction = 3;

    boolean m_alive = true;

    public Timeline m_timeline, m_bombspawntl, m_bombdonetl;

    public Random m_rand = new Random();

    Map<String, Circle> m_snakebody = new HashMap<>();

    Map<Integer, Double> m_xpositions = new HashMap<>();
    Map<Integer, Double> m_ypositions = new HashMap<>();

    int m_gameticks = 0;

    public boolean m_newfood = false;

    static double m_xbound, m_ybound;

    public boolean m_isbombs;


    Bomb m_bomb, m_bomb1, m_bomb2;

    int m_bombspawn, m_difficulty, m_speed;

    Wall m_wall;

    int m_wallticks;

    Timeline m_walltl;

    boolean m_hit = false;

    boolean m_intersects;

    double m_time = 0.3;

    public static String GetScore() {
        return Integer.toString(m_score);
    }

    public static Double GetXBound(){
        return m_xbound;
    }

    public static Double GetYBound(){
        return m_ybound;
    }

    public void initialize(){
        PlayPaneSky.setId(StartScreenController.GetBackground());
        NameLabel.setText("Player Name: "+StartScreenController.GetPlayerName());
        m_time = StartScreenController.GetSpeed();
        snakehead.setStyle("-fx-fill: "+StartScreenController.m_snakecol +";");
        new MusicPlayer("src/Resources/frogger.mp3");
        m_wallticks = 0;
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
        m_bomb = new Bomb(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
        m_bomb1 = new Bomb(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
        m_bomb2 = new Bomb(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
        m_wall = new Wall(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
        sclab.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        sclabnum.setStyle("-fx-text-fill: "+StartScreenController.GetScoreCol()+";");
        StartScreenJFX.m_scene.addEventHandler(KeyEvent.KEY_PRESSED, this::KeyPressed);
        m_speed = 5;
        MainTimeline();
        m_food = new Food(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
        BombTimelines();
        WallTimeline();
    }

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
            m_intersects = CheckBounds(m_food.m_food);
            if (m_intersects) {
                m_score +=521;
                m_food.MoveFood();
                m_newfood = true;
            }
            m_intersects = CheckBounds(m_wall.m_wall);
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
                CheckBomb(m_bomb.m_bomb);
                CheckBomb(m_bomb1.m_bomb);
                CheckBomb(m_bomb2.m_bomb);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sclabnum.setText(Integer.toString(m_score));
            if(m_direction == 0){
                SetPositions(snakehead.getLayoutX()-1,snakehead.getLayoutY()+2);
            } else if (m_direction == 1) {
                SetPositions(snakehead.getLayoutX()-2,snakehead.getLayoutY()-3);
            } else if (m_direction == 2) {
                SetPositions(snakehead.getLayoutX()-1,snakehead.getLayoutY());
            } else if (m_direction == 3) {
                SetPositions(snakehead.getLayoutX()-4,snakehead.getLayoutY());
            }
            m_gameticks++;
        }));
        m_timeline.setCycleCount(Timeline.INDEFINITE);
        m_timeline.play();
    }

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
    public void WallTimeline(){
        m_walltl = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            if(m_hit){
                m_wall = new Wall(m_rand.nextInt((int) m_xbound), m_rand.nextInt((int) m_ybound), PlayPaneSky);
            } else
                m_wall.moveWall();
            m_hit = false;
            m_wallticks++;
        }));
        m_walltl.setCycleCount(Timeline.INDEFINITE);
        m_walltl.play();
    }
    public void SetPositions(double x, double y){
        m_xpositions.put(m_gameticks, x);
        m_ypositions.put(m_gameticks, y);
    }

    public void SetVariables(double x, double y, int b){
        m_bombspawn = b;
        m_xbound = x;
        m_ybound = y;
    }

    public void CheckBomb(ImageView thebomb) throws IOException {
        if(thebomb.isVisible()){
            m_intersects = CheckBounds(thebomb);
            if(m_intersects){
                ToEndScreen();
            }
        }
    }

    public void RemoveSnakeBody() throws IOException {
        if(!m_hit){
            if(m_snakebody.size() == 0)
                ToEndScreen();
            PlayPaneSky.getChildren().remove(m_snakebody.get(Integer.toString(m_snakebody.size()-1)));
            m_snakebody.remove(Integer.toString(m_snakebody.size()-1));
            m_score -= 521;
        }
        m_hit = true;
    }

    public boolean CheckBounds(Node n){
        Bounds bound = n.getBoundsInLocal();
        Bounds snakebound = n.localToScene(bound);
        return snakehead.intersects(snakehead.sceneToLocal(snakebound));
    }

    public void AddSnakeBody(){
        Circle circ = new Circle(13);
        circ.setStyle("-fx-fill: "+StartScreenController.m_snakecol +";");
        PlayPaneSky.getChildren().add(circ);
        m_snakebody.put(Integer.toString(m_snakebody.size()),circ);
        m_newfood = false;
    }
    double x;
    double y;
    public void moveSnakeBody(Circle bodypart, int num){
        x = m_xpositions.get(m_gameticks -((m_speed)*num));
        y = m_ypositions.get(m_gameticks -((m_speed)*num));
        bodypart.setLayoutX(x);
        bodypart.setLayoutY(y);
    }
    public void ToEndScreen() throws IOException {
        m_alive = false;
        m_timeline.stop();
        StartScreenJFX.setRoot("EndScreen");
    }

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
    private void outofBounds() throws IOException {
        boolean xOut = (snakehead.getLayoutX() <= 0 || snakehead.getLayoutX() >= m_xbound);
        boolean yOut = (snakehead.getLayoutY() <= 0 || snakehead.getLayoutY() >= m_ybound);

        if (xOut || yOut)
        {
            ToEndScreen();
        }
    }
}