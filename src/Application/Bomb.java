package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Bomb {

    ImageView m_bomb;

    public Bomb(int x, int y, Pane contpane, Image img) {
        m_bomb = new ImageView();
        m_bomb.setImage(img);
        m_bomb.setLayoutX(x- m_bomb.getFitWidth());
        m_bomb.setLayoutY(y- m_bomb.getFitHeight());
        m_bomb.setFitWidth(70);
        m_bomb.setFitHeight(70);
        m_bomb.setVisible(false);
        contpane.getChildren().add(m_bomb);
        }

    public void BombSpawn(){
        if(!m_bomb.isVisible()){
            Random rand = new Random();
            double x = PlayScreenController.GetXBound();
            double y = PlayScreenController.GetYBound();
            int randx = rand.nextInt(((int)x)-(int) m_bomb.getFitWidth());
            int randy = rand.nextInt(((int)y)-(int) m_bomb.getFitHeight());
            m_bomb.setLayoutX(randx);
            m_bomb.setLayoutY(randy);
            m_bomb.setVisible(true);
        }
    }
    public void BombEnd(){
        if(m_bomb.isVisible()){
            m_bomb.setVisible(false);
        }
    }
}