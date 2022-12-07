import static org.junit.jupiter.api.Assertions.*;

import Application.Obstacles.Bomb;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BombTests {
    Bomb bomb;

    ImageView bombview;

    @BeforeEach
    public void init(){
        Pane pane = new Pane();
        Image img = null;
        bomb = new Bomb(pane, img);
        bombview = bomb.GetM_bomb();
    }

    @Nested
    class Constructor{
        @Test
        void IsVisible(){
            assertEquals(false, bombview.isVisible());
        }
        @Test
        void CheckWidth(){
            assertEquals(70, bombview.getFitWidth());
        }

        @Test
        void CheckHeight(){
            assertEquals(70, bombview.getFitHeight());
        }

        @Test
        void CheckImg(){
            assertEquals(null, bombview.getImage());
        }
    }

    @Nested
    class BombSpawn{
        @Test
        void BombAlreadyVisible(){
            bombview.setVisible(true);
            bomb.BombSpawn(new Rectangle());
            assertEquals(true, bombview.isVisible());
        }
        @Test
        void BombNotVisible(){
            bombview.setVisible(false);
            bomb.BombSpawn(new Rectangle());
            assertEquals(true, bombview.isVisible());
        }
    }

    @Nested
    class BombEnd{
        @Test
        void BombVisible(){
            bombview.setVisible(true);
            bomb.BombEnd();
            assertEquals(false, bombview.isVisible());
        }
        @Test
        void BombNotVisible(){
            bombview.setVisible(false);
            bomb.BombEnd();
            assertEquals(false, bombview.isVisible());
        }
    }
}
