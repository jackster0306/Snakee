package example;

import java.awt.Graphics;
import java.util.Random;

public class Food extends MyFrame.SnakeObject
{

	private static final long m_Food_serialVersionUID = -3641221053272056036L;

	/**
	 * Can't add a setter for m_Food_serialVersionUID because it is final
	 */
	public final long GetFoodSerialVersionUID(){
		return m_Food_serialVersionUID;
	}


	/**
	 * Food method
	 * This creates a food and places it on a random location in the bounds of the frame
	 */
	public Food()	{
		/**
		 * Sets the food to being able to be visible/alive
		 */
		this.l = true;

		/**
		 * Gets an image at random from the food m_ImageUtil_images and sets the food that image
		 */
		this.i = ImageUtil.m_ImageUtil_images.get(String.valueOf(new Random().nextInt(10)));

		/**
		 * Gets the width and height of the chosen image and sets the food that width and height
		 */
		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		/**
		 * It gets a random x and y coordinates in the boundaries and sets the food's x and y coordinates to these values
		 */
		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	/**
	 * Eaten method
	 * @param mySnake
	 * This method is called when food has been 'Eaten' by the snake
	 */
	public void Eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.GetRectangle().intersects(this.GetRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.ChangeLength(mySnake.GetLength() + 1);
			mySnake.score += 521;
		}
		/**
		 * It checks to see whether the snake intersects with the food
		 * If the snake intersects with the food, it sets the food to no longer being visible/alive (l to false)
		 * and the snakes length is increased by one and the score increases
		 */
	}

	/**
	 * Draw Method
	 * @param g
	 * Draws the food onto the frame
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
