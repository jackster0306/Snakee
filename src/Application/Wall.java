package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Class for a Wall
 * Creates, removes and moves Walls
 */
public class Wall {
    //Class Variables
    ImageView m_wall;
    Pane m_pane;
    double m_xbound, m_ybound;
    Random m_rand = new Random();

    //Getters
    public ImageView GetM_wall(){return m_wall;}

    /**
     * Creates and spawns a Wall
     * Sets the x and y co-ordinates of the Wall, as well as the size and image
     * Makes the wall visible and adds it to the Pane
     * @param x the x bound, the maximum the x co-ordinate should be
     * @param y the y bound, the maximum the y co-ordinate should be
     * @param pane the pane to add the Wall to
     * @param img the image to set the Wall to
     */
    public Wall(double x, double y, Pane pane, Image img) {
        this.m_pane = pane;
        m_wall = new ImageView();
        m_wall.setFitWidth(50);
        m_wall.setFitHeight(70);
        m_xbound = PlayScreenController.GetXBound() - m_wall.getFitWidth();
        m_ybound = PlayScreenController.GetYBound() - m_wall.getFitHeight();
        m_wall.setImage(img);
        m_wall.setLayoutX(m_xbound);
        m_wall.setLayoutY(m_ybound);
        m_wall.setVisible(true);
        pane.getChildren().add(m_wall);
    }

    /**
     * Moves the wall
     */
    public void moveWall(){
        m_wall.setLayoutX(m_rand.nextInt((int) m_xbound)- m_wall.getFitWidth());
        m_wall.setLayoutY(m_rand.nextInt((int) m_ybound)- m_wall.getFitHeight());
        if(m_wall.isDisable())
            m_wall.setDisable(false);
    }

    /**
     * Removes the wall from the Pane
     */
    public void removeWall(){
        m_pane.getChildren().remove(m_wall);
    }
}
