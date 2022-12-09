package com.Snake.Models;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Gets images from a Map using an Integer key
 * Each key relates to a particular image
 * @author Jack Gribble
 */
public class ImageUtil {
    //Class Variables
    private  static Map<Integer, Image> m_images = new HashMap<>();

    /**
     * Gets the images Map
     * @return the images map
     */
    public static Map<Integer, Image> GetM_images(){return m_images;}


    /**
     * Contains all the food images
     * These images are accessed using the Integer key they are given
     * Which allows a random image to be chosen
     */
    static{
        m_images.put(0, new Image(ImageUtil.class.getResource("/com/Snake/Images/food-apple.png").toString()));
        m_images.put(1, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-banana.png").toString())));
        m_images.put(2, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-blueberry.png").toString())));
        m_images.put(3, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-cherry.png").toString())));
        m_images.put(4, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-durian.png").toString())));
        m_images.put(5, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-grape.png").toString())));
        m_images.put(6, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-grapefruit.png").toString())));
        m_images.put(7, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-kiwi.png").toString())));
        m_images.put(8, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-lemon.png").toString())));
        m_images.put(9, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-litchi.png").toString())));
        m_images.put(10, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-mango.png").toString())));
        m_images.put(11, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-orange.png").toString())));
        m_images.put(12, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-peach.png").toString())));
        m_images.put(13, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-pear.png").toString())));
        m_images.put(14, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-pineapple.png").toString())));
        m_images.put(15, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-pitaya.png").toString())));
        m_images.put(16, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-strawberry.png").toString())));
        m_images.put(17, new Image((ImageUtil.class.getResource("/com/Snake/Images/food-watermelon.png").toString())));
    }
}
