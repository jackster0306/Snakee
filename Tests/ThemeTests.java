import static org.junit.jupiter.api.Assertions.*;

import Application.Theme;
import org.junit.jupiter.api.Test;

public class ThemeTests {
    Theme theme;

    @Test
    void SnakeTheme(){
        theme = new Theme(0);
        assertEquals("PlayPaneSky", Theme.GetBackground());
        assertEquals("Resources/brick-clipart-briks-10.png", Theme.GetWallImg());
        assertEquals("Resources/skull_bomb_by_alishavolkman_daf7ku4.png", Theme.GetBombImg());
        assertEquals("Resources/food-apple.png", Theme.GetFoodImg());
        assertEquals("Resources/snake-head-right.png", Theme.GetSnakeHImg());
        assertEquals("Resources/snake-body.png", Theme.GetSnakeBImg());
        assertEquals(true, Theme.GetSnakeTheme());
    }
}
