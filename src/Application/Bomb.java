package Application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Bomb {

    ImageView bomb;




    public Bomb(int x, int y, Pane contpane) {
        bomb = new ImageView();
        bomb.setImage(ImageUtil.images.get(18));
        bomb.setLayoutX(x-bomb.getFitWidth());
        bomb.setLayoutY(y-bomb.getFitHeight());
        bomb.setFitWidth(50);
        bomb.setFitHeight(50);
        bomb.setVisible(false);
        contpane.getChildren().add(bomb);
        }

    public void BombSpawn(){
        if(!bomb.isVisible()){
            Random rand = new Random();
            double x = PlayScreenController.GetXBound();
            double y = PlayScreenController.GetYBound();
            int randx = rand.nextInt(((int)x)-(int)bomb.getFitWidth());
            int randy = rand.nextInt(((int)y)-(int)bomb.getFitHeight());
            bomb.setLayoutX(randx);
            bomb.setLayoutY(randy);
            bomb.setVisible(true);
        }
    }
    public void BombEnd(){
        if(bomb.isVisible()){
            bomb.setVisible(false);
        }
    }
}
