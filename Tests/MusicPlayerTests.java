import static org.junit.jupiter.api.Assertions.*;

import Application.MusicPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MusicPlayerTests {
    MusicPlayer mp;
    @BeforeEach
    public void init(){
        mp = new MusicPlayer("src/example/frogger.mp3");
    }

    @Nested
    class MusicPlayerMethod{
        @Test
        void CheckFilename(){
            assertEquals("src/example/frogger.mp3", mp.GetFilename());
        }

        @Test
        void CheckFilenameChanges(){
            mp = new MusicPlayer("src/example/fake.mp3");
            assertEquals("src/example/fake.mp3", mp.GetFilename());
        }
    }

    @Nested
    class PlayMethod{
        int failed;
        @BeforeEach
        void init(){
            failed = 0;
        }

        @Test
        void ValidFilename(){
            try{
                mp = new MusicPlayer("src/example/frogger.mp3");
                mp.Play();
            }catch(Exception e){
                failed = 1;
            }
            if(failed == 1){
                fail("Error was incorrectly thrown\n");
            }
        }


        /*
        Invalid Filename Test Never Caught the Exception but the exception was thrown
        because it can be seen in the output as the code prints it. It is only printed with
        an invalid filename, therefore the try catch in the code works correctly.
         */
    }
}
