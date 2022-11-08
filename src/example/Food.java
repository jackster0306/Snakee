package example;

import java.awt.Graphics;
import java.util.Random;

public class Food extends MyFrame.SnakeObject
{

	private static final long m_Food_serialVersionUID = -3641221053272056036L;

	/**
	 * Food method
	 * This creates a m_Play_food and places it on a random location in the bounds of the frame
	 */
	public Food()	{
		/**
		 * Sets the m_Play_food to being able to be visible/alive
		 */
		this.l = true;

		/**
		 * Gets an image at random from the m_Play_food m_ImageUtil_images and sets the m_Play_food that image
		 */
		this.i = ImageUtil.m_ImageUtil_images.get(String.valueOf(new Random().nextInt(10)));

		/**
		 * Gets the width and height of the chosen image and sets the m_Play_food that width and height
		 */
		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		/**
		 * It gets a random x and y coordinates in the boundaries and sets the m_Play_food's x and y coordinates to these values
		 */
		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	/**
	 * Eaten method
	 * @param mySnake
	 * This method is called when m_Play_food has been 'Eaten' by the snake
	 */
	public void Eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.GetRectangle().intersects(this.GetRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.ChangeLength(mySnake.GetLength() + 1);
			mySnake.score += 521;
		}
		/**
		 * It checks to see whether the snake intersects with the m_Play_food
		 * If the snake intersects with the m_Play_food, it sets the m_Play_food to no longer being visible/alive (l to false)
		 * and the snakes length is increased by one and the score increases
		 */
	}

	/**
	 * Draw Method
	 * @param g
	 * Draws the m_Play_food onto the frame
	 */

	@Override
	public void Draw(Graphics g)
	{
		/**
		 * Draws image i at co-ordinates x y
		 */
		g.drawImage(i, x, y, null);
	}
}
