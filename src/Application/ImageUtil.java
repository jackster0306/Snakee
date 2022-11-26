package Application;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
    public static Map<Integer, Image> images = new HashMap<>();

    static{
        images.put(0, new Image("Resources/food-apple.png"));
        images.put(1, new Image("Resources/food-banana.png"));
        images.put(2, new Image("Resources/food-blueberry.png"));
        images.put(3, new Image("Resources/food-cherry.png"));
        images.put(4, new Image("Resources/food-durian.png"));
        images.put(5, new Image("Resources/food-grape.png"));
        images.put(6, new Image("Resources/food-grapefruit.png"));
        images.put(7, new Image("Resources/food-kiwi.png"));
        images.put(8, new Image("Resources/food-lemon.png"));
        images.put(9, new Image("Resources/food-litchi.png"));
        images.put(10, new Image("Resources/food-mango.png"));
        images.put(11, new Image("Resources/food-orange.png"));
        images.put(12, new Image("Resources/food-peach.png"));
        images.put(13, new Image("Resources/food-pear.png"));
        images.put(14, new Image("Resources/food-pineapple.png"));
        images.put(15, new Image("Resources/food-pitaya.png"));
        images.put(16, new Image("Resources/food-strawberry.png"));
        images.put(17, new Image("Resources/food-watermelon.png"));
        images.put(18, new Image("Resources/skull_bomb_by_alishavolkman_daf7ku4.png"));
        images.put(19, new Image("Resources/brick-clipart-briks-10.png"));
    }
}
