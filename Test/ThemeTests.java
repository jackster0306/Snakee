import com.Snake.Models.Theme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThemeTests {
    Theme theme;

    @Test
    void SnakeTheme(){
        theme = new Theme(0);
        assertEquals("PlayPaneSky", Theme.GetBackground());
        assertEquals("/com/Snake/Images/brick-clipart-briks-10.png", Theme.GetWallImg());
        assertEquals("/com/Snake/Images/skull_bomb_by_alishavolkman_daf7ku4.png", Theme.GetBombImg());
        assertEquals("/com/Snake/Images/food-apple.png", Theme.GetFoodImg());
        assertEquals("/com/Snake/Images/snake-head-right.png", Theme.GetSnakeHImg());
        assertEquals("/com/Snake/Images/snake-body.png", Theme.GetSnakeBImg());
        assertEquals("src/main/resources/com/Snake/Music/eatingaudio.mp3", Theme.GetFoodSound());
        assertEquals("src/main/resources/com/Snake/Music/wallhitaudio.mp3", Theme.GetWallSound());
        assertTrue(Theme.GetSnakeTheme());
    }

    @Test
    void BasketballTheme(){
        theme = new Theme(1);
        assertEquals("PlayPaneBB", Theme.GetBackground());
        assertEquals("/com/Snake/Images/lebroninjured.png", Theme.GetWallImg());
        assertEquals("/com/Snake/Images/MichaelJordan.png", Theme.GetBombImg());
        assertEquals("/com/Snake/Images/Basketball.png", Theme.GetFoodImg());
        assertEquals("/com/Snake/Images/LeBron.png", Theme.GetSnakeHImg());
        assertEquals("/com/Snake/Images/lebroncavsjersey.png", Theme.GetSnakeBImg());
        assertEquals("src/main/resources/com/Snake/Music/Basketball-net-swish.mp3", Theme.GetFoodSound());
        assertEquals("src/main/resources/com/Snake/Music/crowdboo.mp3", Theme.GetWallSound());
        assertFalse(Theme.GetSnakeTheme());
    }

    @Test
    void FootballTheme(){
        theme = new Theme(2);
        assertEquals("PlayPaneFootball", Theme.GetBackground());
        assertEquals("/com/Snake/Images/yellowcard.png", Theme.GetWallImg());
        assertEquals("/com/Snake/Images/redcard.png", Theme.GetBombImg());
        assertEquals("/com/Snake/Images/Football.png", Theme.GetFoodImg());
        assertEquals("/com/Snake/Images/head.png", Theme.GetSnakeHImg());
        assertEquals("/com/Snake/Images/ArgShirt.png", Theme.GetSnakeBImg());
        assertEquals("src/main/resources/com/Snake/Music/crowdcheer.mp3", Theme.GetFoodSound());
        assertEquals("src/main/resources/com/Snake/Music/refereewhistle.mp3", Theme.GetWallSound());
        assertFalse(Theme.GetSnakeTheme());
    }
}
