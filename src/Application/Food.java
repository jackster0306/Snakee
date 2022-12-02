package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Food {
    ImageView m_food;

    Image m_img;

    double m_boundx, m_boundy;

    Random m_rand = new Random();
    public Food(double x, double y, Pane contpane, Image img) {
        m_img = img;
        m_food = new ImageView();
        if(StartScreenController.GetSnakeTheme()){
            m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
        } else{
            m_food.setImage(m_img);
        }
        m_food.setFitWidth(26);
        m_food.setFitHeight(36);
        m_food.setLayoutX(x);
        m_food.setLayoutY(y);
        m_food.setPreserveRatio(true);
        m_food.setSmooth(true);
        this.m_boundx = (PlayScreenController.GetXBound()) - m_food.getFitWidth();
        this.m_boundy = (PlayScreenController.GetYBound()) - m_food.getFitHeight();
        contpane.getChildren().add(m_food);

    }

    public void MoveFood(){
        m_food.setLayoutX(m_rand.nextInt((int) m_boundx));
        m_food.setLayoutY(m_rand.nextInt((int) m_boundy));
        if(StartScreenController.GetSnakeTheme()){
            m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
        }
    }
}