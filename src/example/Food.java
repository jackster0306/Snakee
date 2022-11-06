package example;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;

public class Food extends MyFrame.SnakeObject
{

	private static final long serialVersionUID = -3641221053272056036L;

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
		 * Gets an image at random from the food images and sets the food that image
		 */
		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

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
	 * eaten method
	 * @param mySnake
	 * This method is called when food has been 'eaten' by the snake
	 */
	public void eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
		/**
		 * It checks to see whether the snake intersects with the food
		 * If the snake intersects with the food, it sets the food to no longer being visible/alive (l to false)
		 * and the snakes length is increased by one and the score increases
		 */
	}

	/**
	 * draw Method
	 * @param g
	 * Draws the food onto the frame
	 */

	@Override
	public void draw(Graphics g)
	{
		/**
		 * Draws image i at co-ordinates x y
		 */
		g.drawImage(i, x, y, null);
	}
}
