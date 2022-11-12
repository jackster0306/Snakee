import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import example.*;

import java.awt.*;



public class MyFrameTests {
    MyFrame frame;

    @BeforeEach
    public void init(){
        frame = new MyFrame();
    }

    @Nested
    class LoadFrameMethod{
        @BeforeEach
        public void init(){
            frame.LoadFrame();
        }

        /*
        Noticed that no matter the height and width, the height is always 37 less than what it us set to
        and the width is always 14 less than what it is set to
         */
        @Test
        void CheckWidth(){
            assertEquals(870-14, frame.getSize().getWidth());
        }

        @Test
        void CheckHeight(){
            assertEquals(560-37, frame.getSize().getHeight());
        }

        @Test
        void IsVisible(){
            assertEquals(true, frame.isVisible());
        }
    }
}
