package example;

import java.awt.Graphics;
import java.util.Random;

public class Food extends MyFrame.SnakeObject
{

	private static final long serialVersionUID = -3641221053272056036L;

	/**
	 * Food method
	 * This creates a m_Play_food and places it on a random location in the bounds of the frame
	 */
	public Food()	{
		/**
		 * Sets the m_Play_food to being able to be visible/alive
		 */
		this.m_MyFrame_l = true;

		/**
		 * Gets an image at random from the m_Play_food m_ImageUtil_images and sets the m_Play_food that image
		 */
		this.m_MyFrame_i = ImageUtil.m_ImageUtil_images.get(String.valueOf(new Random().nextInt(10)));

		/**
		 * Gets the width and height of the chosen image and sets the m_Play_food that width and height
		 */
		this.m_MyFrame_w = m_MyFrame_i.getWidth(null);
		this.m_MyFrame_h = m_MyFrame_i.getHeight(null);

		/**
		 * It gets a random m_MyFrame_x and m_MyFrame_y coordinates in the boundaries and sets the m_Play_food's m_MyFrame_x and m_MyFrame_y coordinates to these values
		 */
		this.m_MyFrame_x = (int) (Math.random() * (870 - m_MyFrame_w + 10));
		this.m_MyFrame_y = (int) (Math.random() * (560 - m_MyFrame_h - 40));
	}

	/**
	 * eaten method
	 * @param mySnake
	 * This method is called when m_Play_food has been 'eaten' by the snake
	 */
	public void eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && m_MyFrame_l && mySnake.m_MyFrame_l)		{
			this.m_MyFrame_l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.m_MyFrame_score += 521;
		}
		/**
		 * It checks to see whether the snake intersects with the m_Play_food
		 * If the snake intersects with the m_Play_food, it sets the m_Play_food to no longer being visible/alive (m_MyFrame_l to false)
		 * and the snakes length is increased by one and the m_MyFrame_score increases
		 */
	}

	/**
	 * draw Method
	 * @param g
	 * Draws the m_Play_food onto the frame
	 */

	@Override
	public void draw(Graphics g)
	{
		/**
		 * Draws image m_MyFrame_i at co-ordinates m_MyFrame_x m_MyFrame_y
		 */
		g.drawImage(m_MyFrame_i, m_MyFrame_x, m_MyFrame_y, null);
	}
}
