package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Bomb {
    //Class Variables
    ImageView m_bomb;

    /**
     * Creates and adds the 'bomb' to the Pane provided.
     * Sets an image to the ImageView m_bomb and sets the layout and its height and width
     * Makes the ImageView to not be visible.
     * @param x the max value the 'bombs' x co-ordinate should be, the x boundary
     * @param y the max value the 'bombs' y co-ordinate should be, the y boundary
     * @param contpane the pane to add the 'bomb' to
     * @param img the image to set the 'bomb' to
     */
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

    /**
     * Spawns the 'bomb' when called.
     * Checks if the bomb is currently visible. If not it gets random co-ordinates using the boundaries
     * and sets the x and y co-ordinates of the bomb to these
     * Makes the bomb visible
     * If the bomb is visible, then method does nothing
     */
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

    /**
     * Sets the bomb to visible if the bomb is visible when called
     */
    public void BombEnd(){
        if(m_bomb.isVisible()){
            m_bomb.setVisible(false);
        }
    }
}