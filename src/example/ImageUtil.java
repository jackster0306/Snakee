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

	public Map<String, Image> GetImageUtilImages(){
		return m_ImageUtil_images;
	}

	public void SetImageUtilImages(Map<String, Image> map){
		m_ImageUtil_images = map;
	}



	static
	{
		/**
		 * Adds image links to the variable m_ImageUtil_images
		 * Each image link has a string connected to it to which it can be called
		 */
		// snake
		m_ImageUtil_images.put("snake-head-right", GameUtil.GetImage("example/snake-head-right.png"));
		m_ImageUtil_images.put("snake-body", GameUtil.GetImage("example/snake-body.png"));
		// obstacles
		m_ImageUtil_images.put("0", GameUtil.GetImage("example/food-kiwi.png"));
		m_ImageUtil_images.put("1", GameUtil.GetImage("example/food-lemon.png"));
		m_ImageUtil_images.put("2", GameUtil.GetImage("example/food-litchi.png"));
		m_ImageUtil_images.put("3", GameUtil.GetImage("example/food-mango.png"));
		m_ImageUtil_images.put("4", GameUtil.GetImage("example/food-apple.png"));
		m_ImageUtil_images.put("5", GameUtil.GetImage("example/food-banana.png"));
		m_ImageUtil_images.put("6", GameUtil.GetImage("example/food-blueberry.png"));
		m_ImageUtil_images.put("7", GameUtil.GetImage("example/food-cherry.png"));
		m_ImageUtil_images.put("8", GameUtil.GetImage("example/food-durian.png"));
		m_ImageUtil_images.put("9", GameUtil.GetImage("example/food-grape.png"));
		m_ImageUtil_images.put("10", GameUtil.GetImage("example/food-grapefruit.png"));
		m_ImageUtil_images.put("11", GameUtil.GetImage("example/food-peach.png"));
		m_ImageUtil_images.put("12", GameUtil.GetImage("example/food-pear.png"));
		m_ImageUtil_images.put("13", GameUtil.GetImage("example/food-orange.png"));
		m_ImageUtil_images.put("14", GameUtil.GetImage("example/food-pineapple.png"));
		m_ImageUtil_images.put("15", GameUtil.GetImage("example/food-strawberry.png"));
		m_ImageUtil_images.put("16", GameUtil.GetImage("example/food-watermelon.png"));
		m_ImageUtil_images.put("UI-Sky-background", GameUtil.GetImage("example/UI-background.png"));
		m_ImageUtil_images.put("UI-CartoonSky-background", GameUtil.GetImage("example/UI-background2.png"));
		m_ImageUtil_images.put("game-scene-01", GameUtil.GetImage("example/game-scene-01.jpg"));
	}
}
