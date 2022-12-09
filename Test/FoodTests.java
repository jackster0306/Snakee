import com.Snake.Controllers.PlayScreenController;
import com.Snake.Models.Obstacles.Food;
import com.Snake.Models.Theme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FoodTests {

    Food food;

    ImageView foodview;



    @BeforeEach
    public void init(){
        PlayScreenController.SetXBound(870);
        PlayScreenController.SetYBound(560);
        Theme.SetSnakeTheme(false);
        AnchorPane pane = new AnchorPane();
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
            food.MoveFood(new Rectangle());
            assertEquals(null, foodview.getImage());
            assertTrue(foodview.isVisible());
        }
    }
}
