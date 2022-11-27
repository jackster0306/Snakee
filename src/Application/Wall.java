package Application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Wall {
    ImageView wall;
    Pane pane;

    double xbound, ybound;

    Random rand = new Random();


    public Wall(double x, double y, Pane pane) {
        this.pane = pane;
        wall = new ImageView();
        wall.setFitWidth(50);
        wall.setFitHeight(70);
        wall.setImage(ImageUtil.images.get(19));
        wall.setLayoutX(x);
        wall.setLayoutY(y);
        wall.setVisible(true);
        xbound = PlayScreenController.GetXBound();
        ybound = PlayScreenController.GetYBound();
        pane.getChildren().add(wall);
    }

    public void moveWall(){
        wall.setLayoutX(rand.nextInt((int)xbound)-wall.getFitWidth());
        wall.setLayoutY(rand.nextInt((int)ybound)-wall.getFitHeight());
        if(wall.isDisable())
            wall.setDisable(false);
    }

    public void removeWall(){
        pane.getChildren().remove(wall);
    }
}
