package Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food {
    private Pane pane;
    ImageView food;
    private Color color = Color.GREEN;

    double boundx, boundy;

    Random rand = new Random();
    public Food(double x, double y, Pane contpane) {
        pane = contpane;
        food = new ImageView();
        food.setImage(ImageUtil.images.get(rand.nextInt(17)));
        food.setFitWidth(26);
        food.setFitHeight(36);
        food.setLayoutX(x);
        food.setLayoutY(y);
        food.setPreserveRatio(true);
        food.setSmooth(true);
        this.boundx = (PlayScreenController.GetXBound()) - food.getFitWidth();
        this.boundy = (PlayScreenController.GetYBound()) - food.getFitHeight();
        pane.getChildren().add(food);

    }

    public void MoveFood(){
        food.setLayoutX(rand.nextInt((int)boundx));
        food.setLayoutY(rand.nextInt((int)boundy));
        food.setImage(ImageUtil.images.get(rand.nextInt(17)));
    }
}
