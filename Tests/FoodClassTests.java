import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import example.*;

public class FoodClassTests {
    Food food;

    @BeforeEach
    public void init(){
        food = new Food();
    }

    @Nested
    class FoodMethod{
        @Test
        void IsLTrue(){
            assertEquals(true, food.l);
        }

        @Test
        void SetLFalse(){
            food.l = false;
            assertEquals(false, food.l);
        }

        @Test
        void NewFood(){
            food.l = false;
            food = new Food();
            assertEquals(true, food.l);
        }

        @Test
        void HeightWidthXY(){
            assertTrue(food.GetW() > 0);
            assertTrue(food.GetH() > 0);
            assertTrue(food.GetX() > 0);
            assertTrue(food.GetY() > 0);
        }
    }

    @Nested
    class EatenMethod{
        MySnake snake;
        @BeforeEach
        public void initeaten(){
            snake = new MySnake(food.GetX(),food.GetY());
            snake.score = 0;
            snake.ChangeLength(1);
            food.l = true;
        }

        @Test
        void CheckL(){
            food.Eaten(snake);
            assertEquals(false, food.l);
        }

        @Test
        void CheckScore(){
            food.Eaten(snake);
            assertEquals(521, snake.score);
        }

        @Test
        void CheckLength(){
            food.Eaten(snake);
            assertEquals(2, snake.GetLength());
        }

        @Test
        void SetLFalse(){
            food.l = false;
            food.Eaten(snake);
            assertEquals(1, snake.GetLength());
            assertEquals(0, snake.score);
            assertEquals(false, food.l);
        }
    }




}
