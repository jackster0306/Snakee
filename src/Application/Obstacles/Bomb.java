package Application.Obstacles;

import Application.Controllers.PlayScreenController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Bomb class that is used to create, spawn and remove bombs
 */
public class Bomb {
    //Class Variables
    Random m_rand = new Random();
    ImageView m_bomb;

    public ImageView GetM_bomb(){return  m_bomb;}

    double m_xbound;

    double m_ybound;

    /**
     * Creates and adds the 'bomb' to the Pane provided.
     * Sets an image to the ImageView m_bomb and sets the layout and its height and width
     * Makes the ImageView to not be visible.
     * @param contpane the pane to add the 'bomb' to
     * @param img the image to set the 'bomb' to
     */
    public Bomb(Pane contpane, Image img) {
        m_bomb = new ImageView();
        m_bomb.setImage(img);
        m_bomb.setFitWidth(70);
        m_bomb.setFitHeight(70);
        m_xbound = (PlayScreenController.GetXBound() - m_bomb.getFitWidth());
        m_ybound = (PlayScreenController.GetYBound() - m_bomb.getFitHeight());
        m_bomb.setLayoutX(m_rand.nextInt((int)m_xbound));
        m_bomb.setLayoutY(m_rand.nextInt((int)m_ybound));
        m_bomb.setVisible(false);
        contpane.getChildren().add(m_bomb);
        }

    /**
     * Spawns the 'bomb' when called.
     * Checks if the bomb is currently visible. If not it gets random co-ordinates using the boundaries
     * and sets the x and y co-ordinates of the bomb to these
     * Makes the bomb visible
     * If the bomb is visible, then method does nothing
     */
    public void BombSpawn(){
        if(!m_bomb.isVisible()){
            m_bomb.setLayoutX(m_rand.nextInt((int)m_xbound));
            m_bomb.setLayoutY(m_rand.nextInt((int)m_ybound));
            m_bomb.setVisible(true);
        }
    }

    /**
     * Sets the bomb to visible if the bomb is visible when called
     */
    public void BombEnd(){
        if(m_bomb.isVisible()){
            m_bomb.setVisible(false);
        }
    }
}