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
	 * It gets an image at random from the food images and sets itself that image
	 * Then it gets the width and height of the chosen image and sets itself that width and height
	 * It gets a random x and y coordinates in the boundaries and sets its x and y coordinates to these values
	 */
	public Food()	{
		this.l = true;

		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	/**
	 * eaten method
	 * This method is called when food has been 'eaten' by the snake
	 * It checks to see whether the snake intersects with the food
	 * If it did, the food is no longer visible (setting l to false),
	 * the snakes length is increased by one and the score increases
	 */
	public void eaten(MyFrame.MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}

	//Draws the food onto the frame
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, x, y, null);
	}
}
