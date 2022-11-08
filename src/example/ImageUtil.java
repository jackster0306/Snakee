package example;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	/**
	 * Creates variable m_ImageUtil_images that is a Map of String and Image
	 */
	public static Map<String, Image> m_ImageUtil_images = new HashMap<>();



	static
	{
		/**
		 * Adds image links to the variable m_ImageUtil_images
		 * Each image link has a string connected to it to which it can be called
		 */
		// snake
		m_ImageUtil_images.put("snake-head-m_MyFrame_right", GameUtil.getImage("example/snake-head-right.png"));
		m_ImageUtil_images.put("snake-body", GameUtil.getImage("example/snake-body.png"));
		// obstacles
		m_ImageUtil_images.put("0", GameUtil.getImage("example/food-kiwi.png"));
		m_ImageUtil_images.put("1", GameUtil.getImage("example/food-lemon.png"));
		m_ImageUtil_images.put("2", GameUtil.getImage("example/food-litchi.png"));
		m_ImageUtil_images.put("3", GameUtil.getImage("example/food-mango.png"));
		m_ImageUtil_images.put("4", GameUtil.getImage("example/food-apple.png"));
		m_ImageUtil_images.put("5", GameUtil.getImage("example/food-banana.png"));
		m_ImageUtil_images.put("6", GameUtil.getImage("example/food-blueberry.png"));
		m_ImageUtil_images.put("7", GameUtil.getImage("example/food-cherry.png"));
		m_ImageUtil_images.put("8", GameUtil.getImage("example/food-durian.png"));
		m_ImageUtil_images.put("9", GameUtil.getImage("example/food-grape.png"));
		m_ImageUtil_images.put("10", GameUtil.getImage("example/food-grapefruit.png"));
		m_ImageUtil_images.put("11", GameUtil.getImage("example/food-peach.png"));
		m_ImageUtil_images.put("12", GameUtil.getImage("example/food-pear.png"));
		m_ImageUtil_images.put("13", GameUtil.getImage("example/food-orange.png"));
		m_ImageUtil_images.put("14", GameUtil.getImage("example/food-pineapple.png"));
		m_ImageUtil_images.put("15", GameUtil.getImage("example/food-strawberry.png"));
		m_ImageUtil_images.put("16", GameUtil.getImage("example/food-watermelon.png"));
		m_ImageUtil_images.put("UI-m_Play_background", GameUtil.getImage("example/UI-background.png"));
		m_ImageUtil_images.put("game-scene-01", GameUtil.getImage("example/game-scene-01.jpg"));
	}
}
