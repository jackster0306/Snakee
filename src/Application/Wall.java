package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Wall {
    ImageView m_wall;
    Pane m_pane;

    double m_xbound, m_ybound;

    Random m_rand = new Random();


    public Wall(double x, double y, Pane pane, Image img) {
        this.m_pane = pane;
        m_wall = new ImageView();
        m_wall.setFitWidth(50);
        m_wall.setFitHeight(70);
        m_wall.setImage(img);
        m_wall.setLayoutX(x);
        m_wall.setLayoutY(y);
        m_wall.setVisible(true);
        m_xbound = PlayScreenController.GetXBound();
        m_ybound = PlayScreenController.GetYBound();
        pane.getChildren().add(m_wall);
    }

    public void moveWall(){
        m_wall.setLayoutX(m_rand.nextInt((int) m_xbound)- m_wall.getFitWidth());
        m_wall.setLayoutY(m_rand.nextInt((int) m_ybound)- m_wall.getFitHeight());
        if(m_wall.isDisable())
            m_wall.setDisable(false);
    }

    public void removeWall(){
        m_pane.getChildren().remove(m_wall);
    }
}
