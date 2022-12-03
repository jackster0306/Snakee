import static org.junit.jupiter.api.Assertions.*;

import Application.Bomb;
import Application.PlayScreenController;
import Application.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class WallTests {
    Wall wall;
    ImageView wallview;


    @BeforeEach
    public void init(){
        Pane pane = new Pane();
        Image img = null;
        wall = new Wall(870,560, pane, img);
        wallview = wall.GetM_wall();
    }

    @Nested
    class Constructor{
        @Test
        void IsVisible(){
            assertEquals(true, wallview.isVisible());
        }
        @Test
        void CheckWidth(){
            assertEquals(50, wallview.getFitWidth());
        }

        @Test
        void CheckHeight(){
            assertEquals(70, wallview.getFitHeight());
        }

        @Test
        void CheckLayout(){
            assertEquals(820, wallview.getLayoutX());
            assertEquals(490, wallview.getLayoutY());
        }

        @Test
        void CheckImg(){
            assertEquals(null, wallview.getImage());
        }
    }

    @Nested
    class WallMove{
        @Test
        void BombDisabled(){
            wallview.setDisable(true);
            wall.moveWall();
            assertNotEquals(820, wallview.getLayoutX());
            assertNotEquals(490, wallview.getLayoutY());
            assertEquals(false, wallview.isDisable());
        }
        @Test
        void BombNotDisabled(){
            wallview.setDisable(false);
            wall.moveWall();
            assertNotEquals(820, wallview.getLayoutX());
            assertNotEquals(490, wallview.getLayoutY());
            assertEquals(false, wallview.isVisible());
        }
    }
}
