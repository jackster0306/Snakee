import static org.junit.jupiter.api.Assertions.*;

import Application.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class FoodTests {

    Food food;

    ImageView foodview;



    @BeforeEach
    public void init(){
        PlayScreenController.SetXBound(870);
        PlayScreenController.SetYBound(560);
        Theme.SetSnakeTheme(false);
        Pane pane = new Pane();
        Image img = null;
        food = new Food(870,560, pane, img);
        foodview = food.GetM_food();
    }


    @Nested
    class Constructor{
        @Test
        void IsVisible(){
            assertEquals(true, foodview.isVisible());
        }
        @Test
        void CheckWidth(){
            assertEquals(26, foodview.getFitWidth());
        }

        @Test
        void CheckHeight(){
            assertEquals(36, foodview.getFitHeight());
        }

        @Test
        void CheckLayout(){;
            assertEquals(844, foodview.getLayoutX());
            assertEquals(524, foodview.getLayoutY());
        }

        @Test
        void CheckImgNotSnakeTheme(){
            assertEquals(null, foodview.getImage());
        }
    }

    @Nested
    class MoveFood{

        @Test
        void CheckNotSnakeTheme(){
            food.MoveFood();
            assertNotEquals(844, foodview.getLayoutX());
            assertNotEquals(524, foodview.getLayoutY());
            assertEquals(null, foodview.getImage());
            assertTrue(foodview.isVisible());
        }
    }
}
