import static org.junit.jupiter.api.Assertions.*;

import Application.Bomb;
import Application.PlayScreenController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        bomb = new Bomb(870,560, pane, img);
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
        void CheckLayout(){
            assertEquals(800, bombview.getLayoutX());
            assertEquals(490, bombview.getLayoutY());
        }

        @Test
        void CheckImg(){
            assertEquals(null, bombview.getImage());
        }
    }

    @Nested
    class BombSpawn{
        @BeforeEach
        public  void init(){
            PlayScreenController.SetXBound(870);
            PlayScreenController.SetYBound(560);
        }
        @Test
        void BombAlreadyVisible(){
            bombview.setVisible(true);
            bomb.BombSpawn();
            assertEquals(800, bombview.getLayoutX());
            assertEquals(490, bombview.getLayoutY());
        }
        @Test
        void BombNotVisible(){
            bombview.setVisible(false);
            bomb.BombSpawn();
            assertNotEquals(800, bombview.getLayoutX());
            assertNotEquals(490, bombview.getLayoutY());
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
