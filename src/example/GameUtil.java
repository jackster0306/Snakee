package example;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil
{
	/**
	 * GetImage method
	 * @param imagePath - the link to where the image is stored
	 * This method is used to retrieve the requested image from the resources
	 * If the image does not exist, an error message is printed
	 * If the image exists, it is then returned to the caller
	 * @return i - the image
	 */
	public static Image GetImage(String imagePath)
	{

		/**
		 * Gets the url of image
		 * Sets the image variable i to null
		 */
		URL u = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage i = null;

		/**
		 * A try, catch is used to see if the image given was found
		 * ImageIO.read(u) returns a BufferedImage from decoding the url provided and sets this value to the variable i, which is of type BufferedImage
		 * If image exists, the image is returned
		 * If the image is not found, a message specifying this is printed, alongside the actual error message
		 */
		try
		{
			i = ImageIO.read(u);
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND!\n");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * RotateImage method
	 * @param bufferedImage - the image to rotate
	 * @param degree - the degree to rotate the image by
	 * This method is used to rotate an image
	 * @return i - the rotated image
	 */
	public static Image RotateImage(final BufferedImage bufferedImage, final int degree)
	{

		/**
		 * Gets width of given image and stores it in variable w
		 * Gets height of given image and stores it in variable h
		 * Gets transparency of given image and stores it in variable t
		 */
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int t = bufferedImage.getColorModel().getTransparency();

		/**
		 * Creates 2 local variables i and graphics2d of types BufferedImage and Graphics2D respectively
		 */
		BufferedImage i;
		Graphics2D graphics2d;


		/**
		 * Assigns value to newly created variable
		 * Creates a new image of width w, height h and transparency t, which is stored in the variable i
		 * The image created with the given rendering is stored in variable graphics2d
		 */
		//161 Character in below line
		(graphics2d = (i = new BufferedImage(w, h, t)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


		/**
		 * Rotates the image in variable graphics2d by the degree given in the parameters
		 * Then redraws the image after it has been rotated
		 * Dispose of the image in graphic2d and releases any resources it was using
		 */
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedImage, 0, 0, null);
		graphics2d.dispose();


		return i;

	}
}
