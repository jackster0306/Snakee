package Application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Food {
    ImageView m_food;

    double m_boundx, m_boundy;

    Random m_rand = new Random();
    public Food(double x, double y, Pane contpane) {
        m_food = new ImageView();
        m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
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
        m_food.setImage(ImageUtil.m_images.get(m_rand.nextInt(17)));
    }
}