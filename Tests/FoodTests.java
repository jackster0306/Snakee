import static org.junit.jupiter.api.Assertions.*;

import Application.*;
import Application.Controllers.PlayScreenController;
import Application.Obstacles.Food;
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
        food = new Food(pane, img);
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
        void CheckImgNotSnakeTheme(){
            assertEquals(null, foodview.getImage());
        }
    }

    @Nested
    class MoveFood{

        @Test
        void CheckNotSnakeTheme(){
            food.MoveFood();
            assertEquals(null, foodview.getImage());
            assertTrue(foodview.isVisible());
        }
    }
}
