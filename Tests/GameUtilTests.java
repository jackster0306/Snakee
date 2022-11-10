import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.awt.Image;
import java.awt.image.BufferedImage;

import example.*;

public class GameUtilTests {
    example.GameUtil game;

    @BeforeEach
    void init(){
        game = new GameUtil();
    }

    @Nested
    class GetImageMethod{
        Image i;
        @Test
        void ValidFilename(){
            i = game.GetImage("example/snake-head-right.png");
            assertNotNull(i);
        }

        @Test
        void InvalidFilename(){
            try{
                i = game.GetImage("INVALID");
            }catch (Exception e) {
                assertEquals("ERROR : SPECIFIC IMAGE NOT FOUND!\n", e.getMessage());
                assertNull(i);
            }
        }
    }

    @Nested
    class RotateImageMethod{
        BufferedImage bi = (BufferedImage) ImageUtil.m_ImageUtil_images.get("snake-head-right");
        Image i;
        @Test
        void ValidArguments(){
            i = game.RotateImage(bi, 90);
            assertNotNull(i);
        }

        @Test
        void InvalidArgumentsType(){
            int failed = 0;
            try {
                i = game.RotateImage(bi, -90);
            }catch(Exception e){
                failed = 1;
            }
            if (failed == 0){
                fail("Degree was invalid (-90 degrees) and there was no error thrown");
                assertNull(i);
            }

            failed = 0;
            try {
                i = game.RotateImage(bi, 450);
            }catch(Exception e){
                failed = 1;
            }
            if (failed == 0){
                fail("Degree was invalid (450 degrees) and there was no error thrown");
                assertNull(i);
            }
        }
    }
}
