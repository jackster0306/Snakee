package Application;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
    public static Map<Integer, Image> images = new HashMap<>();

    static{
        images.put(0, new Image("example/food-apple.png"));
        images.put(1, new Image("example/food-banana.png"));
        images.put(2, new Image("example/food-blueberry.png"));
        images.put(3, new Image("example/food-cherry.png"));
        images.put(4, new Image("example/food-durian.png"));
        images.put(5, new Image("example/food-grape.png"));
        images.put(6, new Image("example/food-grapefruit.png"));
        images.put(7, new Image("example/food-kiwi.png"));
        images.put(8, new Image("example/food-lemon.png"));
        images.put(9, new Image("example/food-litchi.png"));
        images.put(10, new Image("example/food-mango.png"));
        images.put(11, new Image("example/food-orange.png"));
        images.put(12, new Image("example/food-peach.png"));
        images.put(13, new Image("example/food-pear.png"));
        images.put(14, new Image("example/food-pineapple.png"));
        images.put(15, new Image("example/food-pitaya.png"));
        images.put(16, new Image("example/food-strawberry.png"));
        images.put(17, new Image("example/food-watermelon.png"));
        images.put(18, new Image("Application/skull_bomb_by_alishavolkman_daf7ku4.png"));
        images.put(19, new Image("Application/brick-clipart-briks-10.png"));
    }
}
