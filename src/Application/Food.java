package Application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Food {
    ImageView food;

    double boundx, boundy;

    Random rand = new Random();
    public Food(double x, double y, Pane contpane) {
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
        contpane.getChildren().add(food);

    }

    public void MoveFood(){
        food.setLayoutX(rand.nextInt((int)boundx));
        food.setLayoutY(rand.nextInt((int)boundy));
        food.setImage(ImageUtil.images.get(rand.nextInt(17)));
    }
}
