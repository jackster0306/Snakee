package Application.Obstacles;

import Application.Controllers.PlayScreenController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Bomb class that is used to create, spawn and remove bombs
 * @author Jack Gribble
 */
public class Bomb {
    //Class Variables
    private Random m_rand = new Random();
    private ImageView m_bomb;
    private double m_xbound;
    private double m_ybound;

    //Getters

    /**
     * Gets the 'bomb'
     * @return the bomb
     */
    public ImageView GetM_bomb(){return  m_bomb;}

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
    public void BombSpawn(Rectangle head){
        if(!m_bomb.isVisible()){
            int x = m_rand.nextInt((int) m_xbound);
            int y = m_rand.nextInt((int) m_ybound);
            if(head.getX() == x || head.getY() == y){
                x = m_rand.nextInt((int) m_xbound);
                y = m_rand.nextInt((int) m_ybound);
            }
            m_bomb.setLayoutX(x);
            m_bomb.setLayoutY(y);
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