package Application.Obstacles;

import Application.Controllers.PlayScreenController;
import Application.ImageUtil;
import Application.Theme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Food class that is used to create and move Food
 */
public class Food {
    //Class Variables
    private ImageView m_food;
    Image m_img;
    double m_boundx, m_boundy;
    Random m_rand = new Random();

    public ImageView GetM_food(){return m_food;}

    /**
     * Creates a food for the game
     * Sets the height width and layout as well as an Image for the food
     * @param contpane the pane to add the food to
     * @param img the image to set the food to
     */
    public Food(Pane contpane, Image img) {
        m_img = img;
        m_food = new ImageView();
        if(Theme.GetSnakeTheme()){
            m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
        } else{
            m_food.setImage(m_img);
        }
        m_food.setFitWidth(26);
        m_food.setFitHeight(36);
        this.m_boundx = (PlayScreenController.GetXBound() - m_food.getFitWidth());
        this.m_boundy = (PlayScreenController.GetYBound() - m_food.getFitHeight());
        m_food.setLayoutX(m_rand.nextInt((int)m_boundx));
        m_food.setLayoutY(m_rand.nextInt((int)m_boundy));
        m_food.setPreserveRatio(true);
        m_food.setSmooth(true);
        contpane.getChildren().add(m_food);
    }

    /**
     * Called when the current food is 'eaten' and therefore the food needs to be moved
     * Sets a new x and y co-ordinate using the max bounds given when the food was created
     * Sets a new Image to the food
     */
    public void MoveFood(){
        m_food.setLayoutX((m_rand.nextInt((int) m_boundx)));
        m_food.setLayoutY((m_rand.nextInt((int) m_boundy)));
        if(Theme.GetSnakeTheme()){
            m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
        }
    }
}