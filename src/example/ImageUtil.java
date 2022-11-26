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
		m_ImageUtil_images.put("snake-head-right", GameUtil.GetImage("Resources/snake-head-right.png"));
		m_ImageUtil_images.put("snake-body", GameUtil.GetImage("Resources/snake-body.png"));
		// obstacles
		m_ImageUtil_images.put("0", GameUtil.GetImage("Resources/food-kiwi.png"));
		m_ImageUtil_images.put("1", GameUtil.GetImage("Resources/food-lemon.png"));
		m_ImageUtil_images.put("2", GameUtil.GetImage("Resources/food-litchi.png"));
		m_ImageUtil_images.put("3", GameUtil.GetImage("Resources/food-mango.png"));
		m_ImageUtil_images.put("4", GameUtil.GetImage("Resources/food-apple.png"));
		m_ImageUtil_images.put("5", GameUtil.GetImage("Resources/food-banana.png"));
		m_ImageUtil_images.put("6", GameUtil.GetImage("Resources/food-blueberry.png"));
		m_ImageUtil_images.put("7", GameUtil.GetImage("Resources/food-cherry.png"));
		m_ImageUtil_images.put("8", GameUtil.GetImage("Resources/food-durian.png"));
		m_ImageUtil_images.put("9", GameUtil.GetImage("Resources/food-grape.png"));
		m_ImageUtil_images.put("10", GameUtil.GetImage("Resources/food-grapefruit.png"));
		m_ImageUtil_images.put("11", GameUtil.GetImage("Resources/food-peach.png"));
		m_ImageUtil_images.put("12", GameUtil.GetImage("Resources/food-pear.png"));
		m_ImageUtil_images.put("13", GameUtil.GetImage("Resources/food-orange.png"));
		m_ImageUtil_images.put("14", GameUtil.GetImage("Resources/food-pineapple.png"));
		m_ImageUtil_images.put("15", GameUtil.GetImage("Resources/food-strawberry.png"));
		m_ImageUtil_images.put("16", GameUtil.GetImage("Resources/food-watermelon.png"));
		m_ImageUtil_images.put("UI-Sky-background", GameUtil.GetImage("Resources/UI-background.png"));
		m_ImageUtil_images.put("UI-CartoonSky-background", GameUtil.GetImage("Resources/UI-background2.png"));
		m_ImageUtil_images.put("game-scene-01", GameUtil.GetImage("Resources/game-scene-01.jpg"));
	}
}
