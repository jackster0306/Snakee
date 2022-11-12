import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import example.*;

import java.awt.*;


public class MySnakeTests {
    MySnake snake;
    @BeforeEach
    public void init(){
        snake = new MySnake(100,100);
    }

    @Nested
    class MySnakeMethod{
        @Test
        void IsVisible(){
            assertEquals(true, snake.l);
        }

        @Test
        void CheckXY(){
            assertEquals(100, snake.GetX());
            assertEquals(100, snake.GetY());
        }

        @Test
        void CheckWidth(){
            Image i = snake.GetI();
            assertEquals(i.getHeight(null), snake.GetH());
            assertEquals(i.getWidth(null), snake.GetW());
        }


        @Test
        void CheckSpeedLength(){
            assertEquals(5,snake.GetSpeed());
            assertEquals(1, snake.GetLength());
        }

        @Test
        void CheckNum(){
            assertEquals((snake.GetW()/ 5), snake.GetNum());
        }
    }

    @Test
    void ChangeLengthMethod(){
        snake.ChangeLength(5);
        assertEquals(5, snake.GetLength());
    }

    @Nested
    class KeyPressedMethod{
        // Don't know how to test this as I can't seem to send a KeyEvent
        // From playing the game, this method seems to function completely fine
    }

    @Nested
    class MoveMethod{
        @BeforeEach
        public void init(){
            snake.SetUp(false);
            snake.SetDown(false);
            snake.SetLeft(false);
            snake.SetRight(false);
        }

        @Test
        void CheckUp(){
            snake.SetUp(true);
            snake.move();
            assertEquals(95, snake.GetY());
            assertEquals(100, snake.GetX());
        }

        @Test
        void CheckDown(){
            snake.SetDown(true);
            snake.move();
            assertEquals(105, snake.GetY());
            assertEquals(100, snake.GetX());
        }

        @Test
        void CheckLeft(){
            snake.SetLeft(true);
            snake.move();
            assertEquals(100, snake.GetY());
            assertEquals(95, snake.GetX());
        }

        @Test
        void CheckRight(){
            snake.SetRight(true);
            snake.move();
            assertEquals(100, snake.GetY());
            assertEquals(105, snake.GetX());
        }
    }

    @Nested
    class OutOfBoundsMethod{
        @Test
        void NeitherOutOfBounds(){
            snake = new MySnake(100,100);
            snake.testOOB();
            assertEquals(true, snake.l);
        }

        @Test
        void XOutOfBounds(){
            snake = new MySnake(-5,100);
            snake.testOOB();
            assertEquals(false, snake.l);
            snake = new MySnake(880, 100);
            snake.testOOB();
            assertEquals(false, snake.l);
        }

        @Test
        void YOutOfBounds(){
            snake = new MySnake(100,30);
            snake.testOOB();
            assertEquals(false, snake.l);
            snake = new MySnake(100, 570);
            snake.testOOB();
            assertEquals(false, snake.l);
        }
    }
}
