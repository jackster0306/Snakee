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
	 * getImage method
	 * @param imagePath - the link to where the image is stored
	 * This method is used to retrieve the requested image from the resources
	 * If the image does not exist, an error message is printed
	 * If the image exists, it is then returned to the caller
	 * @return m_MyFrame_i - the image
	 */
	public static Image getImage(String imagePath)
	{

		/**
		 * Gets the url of image
		 * Sets the image variable m_GameUtil_i to null
		 */
		URL m_GameUtil_u = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage m_GameUtil_i = null;

		/**
		 * A try, catch is used to see if the image given was found
		 * ImageIO.read(u) returns a BufferedImage from decoding the url provided and sets this value to the variable m_GameUtil_i, which is of type BufferedImage
		 * If image exists, the image is returned
		 * If the image is not found, a message specifying this is printed, alongside the actual error message
		 */
		try
		{
			m_GameUtil_i = ImageIO.read(m_GameUtil_u);
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND!\n");
			e.printStackTrace();
		}

		return m_GameUtil_i;
	}

	/**
	 * rotateImage method
	 * @param bufferedImage - the image to rotate
	 * @param degree - the degree to rotate the image by
	 * This method is used to rotate an image
	 * @return m_MyFrame_i - the rotated image
	 */
	public static Image rotateImage(final BufferedImage bufferedImage, final int degree)
	{

		/**
		 * Gets width of given image and stores it in variable m_GameUtil_w
		 * Gets height of given image and stores it in variable m_GameUtil_h
		 * Gets transparency of given image and stores it in variable m_GameUtil_t
		 */
		int m_GameUtil_w = bufferedImage.getWidth();
		int m_GameUtil_h = bufferedImage.getHeight();
		int m_GameUtil_t = bufferedImage.getColorModel().getTransparency();

		/**
		 * Creates 2 local variables m_GameUtil_i and m_GameUtil_graphics2d of types BufferedImage and Graphics2D respectively
		 */
		BufferedImage m_GameUtil_i;
		Graphics2D m_GameUtil_graphics2d;


		/**
		 * Assigns value to newly created variable
		 * Creates a new image of width m_GameUtil_w, height m_GameUtil_h and transparency m_GameUtil_t, which is stored in the variable m_GameUtil_i
		 * The image created with the given rendering is stored in variable m_GameUtil_graphics2d
		 */
		(m_GameUtil_graphics2d = (m_GameUtil_i = new BufferedImage(m_GameUtil_w, m_GameUtil_h, m_GameUtil_t)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


		/**
		 * Rotates the image in variable m_GameUtil_graphics2d by the degree given in the parameters
		 * Then redraws the image after it has been rotated
		 * Dispose of the image in graphic2d and releases any resources it was using
		 */
		m_GameUtil_graphics2d.rotate(Math.toRadians(degree), m_GameUtil_w / 2, m_GameUtil_h / 2);
		m_GameUtil_graphics2d.drawImage(bufferedImage, 0, 0, null);
		m_GameUtil_graphics2d.dispose();


		return m_GameUtil_i;

	}
}
