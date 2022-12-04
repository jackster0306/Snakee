import static org.junit.jupiter.api.Assertions.*;

import Application.Obstacles.Wall;
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
        wall = new Wall(pane, img);
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
        void CheckImg(){
            assertEquals(null, wallview.getImage());
        }
    }

    @Nested
    class WallMove{
        @Test
        void WallMoves(){
            double x = wallview.getLayoutX();
            double y = wallview.getLayoutY();
            wall.moveWall();
            assertNotEquals(x, wallview.getLayoutX());
            assertNotEquals(y, wallview.getLayoutY());
        }
    }
}
