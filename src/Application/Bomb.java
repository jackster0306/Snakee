package Application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Bomb {
    double xbound, ybound;




    public Bomb(double x, double y, Pane contpane) {
        xbound = x;
        ybound = y;
        }

    public static void BombSpawn(ImageView img){
        if(!img.isVisible()){
            Random rand = new Random();
            int randx = rand.nextInt(((int)PlayScreenController.xbound)-(int)img.getFitWidth());
            int randy = rand.nextInt(((int)PlayScreenController.ybound)-(int)img.getFitHeight());
            img.setLayoutX(randx);
            img.setLayoutY(randy);
            img.setVisible(true);


        /*
        int randx = rand.nextInt(((int)xbound)-(int)foodview.getFitWidth());
        int randy = rand.nextInt(((int)ybound)-(int)foodview.getFitHeight());
        double x = snakehead.getLayoutX();
        double y = snakehead.getLayoutY();
        while((randx >= (x-10) || randx <= (x+10)) && (randy >= (y-10) || randy <= (y+10))){
            randx = rand.nextInt(((int)xbound)-(int)foodview.getFitWidth());
            randy = rand.nextInt(((int)ybound)-(int)foodview.getFitHeight());
            x = snakehead.getLayoutX();
            y = snakehead.getLayoutY();
        }
        img.setLayoutX(randx);
        img.setLayoutY(randy);
        img.setVisible(true);
         */

        }
    }
}
