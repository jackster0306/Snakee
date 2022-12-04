package Application;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Gets images from a Map using an Integer key
 * Each key relates to a particular image
 */
public class ImageUtil {
    //Class Variables
    public static Map<Integer, Image> m_images = new HashMap<>();


    /**
     * Contains all the food images
     * These images are accessed using the Integer key they are given
     * Which allows a random image to be chosen
     */
    static{
        m_images.put(0, new Image("Resources/Images/food-apple.png"));
        m_images.put(1, new Image("Resources/Images/food-banana.png"));
        m_images.put(2, new Image("Resources/Images/food-blueberry.png"));
        m_images.put(3, new Image("Resources/Images/food-cherry.png"));
        m_images.put(4, new Image("Resources/Images/food-durian.png"));
        m_images.put(5, new Image("Resources/Images/food-grape.png"));
        m_images.put(6, new Image("Resources/Images/food-grapefruit.png"));
        m_images.put(7, new Image("Resources/Images/food-kiwi.png"));
        m_images.put(8, new Image("Resources/Images/food-lemon.png"));
        m_images.put(9, new Image("Resources/Images/food-litchi.png"));
        m_images.put(10, new Image("Resources/Images/food-mango.png"));
        m_images.put(11, new Image("Resources/Images/food-orange.png"));
        m_images.put(12, new Image("Resources/Images/food-peach.png"));
        m_images.put(13, new Image("Resources/Images/food-pear.png"));
        m_images.put(14, new Image("Resources/Images/food-pineapple.png"));
        m_images.put(15, new Image("Resources/Images/food-pitaya.png"));
        m_images.put(16, new Image("Resources/Images/food-strawberry.png"));
        m_images.put(17, new Image("Resources/Images/food-watermelon.png"));
    }
}
