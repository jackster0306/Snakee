package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @Project Snakee
 * @Description Load the game and refresh it constantly
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 


public class MyFrame extends JPanel implements KeyListener
{
	/**
	 * Class MyFrame
	 * Implements KeyListener - used for actions involving keys being pressed
	 * Extends JPanel - JPanel is a general container
	 * This class is the main window when the game is run, it is what the game is played on
	 * Constantly refreshed
	 */

	private static final long serialVersionUID = -3149926831770554380L;

	public JFrame m_MyFrame_jFrame = new JFrame();
	/**
	 * Creates varaible m_MyFrame_jFrame of type JFrame
	 */

	public MyFrame()
	{
		m_MyFrame_jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("snake-logo.png")));

	}

	public void loadFrame()
	{
		/**
		 * loadFrame method
		 * Sets m_MyFrame_up the frame ready to be used
		 */

		/*
		 * Prevent the image from flashing.
		 */
		this.setDoubleBuffered(true);
		m_MyFrame_jFrame.add(this);
		m_MyFrame_jFrame.addKeyListener(this);
		/**
		 * Adds keyListener for m_MyFrame_jFrame
		 */

		m_MyFrame_jFrame.setTitle("Snakee Yipee");
		m_MyFrame_jFrame.setSize(870, 560);
		m_MyFrame_jFrame.setLocationRelativeTo(null);
		m_MyFrame_jFrame.addWindowListener(new WindowAdapter()// loka
				/**
				 * Sets title, size and location of the frame
				 * Adds a windowListener to m_MyFrame_jFrame
 				 */
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				/**
				 * windowClosing method
				 * @param e
				 * It is for when the user closes the window
				 * Exits
				 */
				super.windowClosing(e);
				System.exit(0);
			}
		});
		m_MyFrame_jFrame.setVisible(true);
		/**
		 * Sets the m_MyFrame_jFrame to being visible, so the user can see it
		 */

		new MyThread().start();
	}
	class MyThread extends Thread
	{
		@Override
		public void run()
		{
			/**
			 * run Method
			 * Used to update the m_MyFrame_jFrame
			 */
			super.run();
			while (true)
			{
				repaint();
				try
				{
					sleep(30);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			/**
			 * Constantly updates the m_MyFrame_jFrame for the user, every 30 milliseconds
			 * If there is an error, the try catch will catch it and print the error message
			 */
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	public static class MySnake extends SnakeObject implements movable
	{
		/**
		 * class MySnake
		 * Extends SnakeObject
		 * Implements movable
		 */
		// The game changer.
		private int m_MyFrame_speed_XY;
		private int m_MyFrame_length;
		private int m_MyFrame_num; // ?
		public int m_MyFrame_score = 0;
		/**
		 * Creates the initial variables for the speed and length of the snake
		 * Creates a variable m_MyFrame_num
		 * Creates variable m_MyFrame_score, used for the m_MyFrame_score, and sets it to 0
		 */

		private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.m_ImageUtil_images.get("snake-head-m_MyFrame_right");
		/**
		 * Creates the head of the snake, setting the picture of the snake head to the variable
		 */

		public static List<Point> m_MyFrame_bodyPoints = new LinkedList<>();
		/**
		 * Creates a List of Points which will be used when the snake grows after eating m_Play_food
		 */

		private static BufferedImage m_MyFrame_newImgSnakeHead;
		/**
		 * Creates variable m_MyFrame_newImgSnakeHead
		 */

		boolean m_MyFrame_up, m_MyFrame_down, m_MyFrame_left, m_MyFrame_right = true;
		/**
		 * Creates boolean variables for m_MyFrame_up, m_MyFrame_down, m_MyFrame_left and m_MyFrame_right. Sets them all to true
		 */

		public MySnake(int x, int y)
		{
			this.m_MyFrame_l = true;
			this.m_MyFrame_x = x;
			this.m_MyFrame_y = y;
			this.m_MyFrame_i = ImageUtil.m_ImageUtil_images.get("snake-body");
			this.m_MyFrame_w = m_MyFrame_i.getWidth(null);
			this.m_MyFrame_h = m_MyFrame_i.getHeight(null);
			/**
			 * Sets the co-ordinates of the snake
			 * Adds image for the body of the snake
			 * Gets the width and height of the image and changes the variables m_MyFrame_w and m_MyFrame_h, used for width and height, to these values
			 */

			this.m_MyFrame_speed_XY = 5;
			this.m_MyFrame_length = 1;
			/**
			 * Sets the speed and m_MyFrame_length of the snake
			 */

			/*
			 * Attention : ?
			 */
			this.m_MyFrame_num = m_MyFrame_w / m_MyFrame_speed_XY;
			m_MyFrame_newImgSnakeHead = IMG_SNAKE_HEAD;

		}

		/**
		 * getLength method
		 * Returns the m_MyFrame_length of the snake
		 * @return m_MyFrame_length
		 */
		public int getLength()
		{
			return m_MyFrame_length;
		}

		/**
		 * changeLength method
		 * Changes the m_MyFrame_length of the snake
		 * @param length - m_MyFrame_length to change local variable m_MyFrame_length of the snake to
		 */
		public void changeLength(int length)
		{
			this.m_MyFrame_length = length;
		}

		/**
		 * keyPressed method
		 * Used for when a key is pressed
		 * @param e
		 */
		public void keyPressed(KeyEvent e)
		{
			// Check the key
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				if (!m_MyFrame_down)
				{
					m_MyFrame_up = true;
					m_MyFrame_down = false;
					m_MyFrame_left = false;
					m_MyFrame_right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
				}
				break;
				/**
				 * Case for if the UP key is pressed
				 * Changes the values of m_MyFrame_down, m_MyFrame_left and m_MyFrame_right to false
				 * Changes value of m_MyFrame_up to true
				 * Does this because the snake will now be m_Snake_moving m_MyFrame_up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face m_MyFrame_up
				 */

			case KeyEvent.VK_DOWN:
				if (!m_MyFrame_up)
				{
					m_MyFrame_up = false;
					m_MyFrame_down = true;
					m_MyFrame_left = false;
					m_MyFrame_right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
				}
				break;
				/**
				 * Case for if the DOWN key is pressed
				 * Changes the values of m_MyFrame_up, m_MyFrame_left and m_MyFrame_right to false
				 * Changes value of m_MyFrame_down to true
				 * Does this because the snake will now be m_Snake_moving m_MyFrame_up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face m_MyFrame_down
				 */

			case KeyEvent.VK_LEFT:
				if (!m_MyFrame_right)
				{
					m_MyFrame_up = false;
					m_MyFrame_down = false;
					m_MyFrame_left = true;
					m_MyFrame_right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

				}
				break;
				/**
				 * Case for if the LEFT key is pressed
				 * Changes the values of m_MyFrame_up, m_MyFrame_down and m_MyFrame_right to false
				 * Changes value of m_MyFrame_left to true
				 * Does this because the snake will now be m_Snake_moving m_MyFrame_up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face m_MyFrame_left
				 */

			case KeyEvent.VK_RIGHT:
				if (!m_MyFrame_left)
				{
					m_MyFrame_up = false;
					m_MyFrame_down = false;
					m_MyFrame_left = false;
					m_MyFrame_right = true;

					m_MyFrame_newImgSnakeHead = IMG_SNAKE_HEAD;
				}

			default:
				break;
				/**
				 * Case for if the RIGHT key is pressed
				 * Changes the values of m_MyFrame_up, m_MyFrame_down and m_MyFrame_left to false
				 * Changes value of m_MyFrame_right to true
				 * Does this because the snake will now be m_Snake_moving m_MyFrame_up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face m_MyFrame_right
				 */
			}
		}


		public void move()
		{
			// Let the swarm move
			if (m_MyFrame_up)
			{
				m_MyFrame_y -= m_MyFrame_speed_XY;
			} else if (m_MyFrame_down)
			{
				m_MyFrame_y += m_MyFrame_speed_XY;
			} else if (m_MyFrame_left)
			{
				m_MyFrame_x -= m_MyFrame_speed_XY;
			} else if (m_MyFrame_right)
			{
				m_MyFrame_x += m_MyFrame_speed_XY;
			}
			/**
			 * Uses the boolean variables m_MyFrame_up, m_MyFrame_down, m_MyFrame_left and m_MyFrame_right to see which way the snake is m_Snake_moving
			 * If m_Snake_moving m_MyFrame_up, lowers the m_MyFrame_y co-ordinate by the speed of the snake
			 * If m_Snake_moving m_MyFrame_down, raises the m_MyFrame_y co-ordinate by the speed of the snake
			 * If m_Snake_moving m_MyFrame_left, lowers the m_MyFrame_x co-ordinate by the speed of the snake
			 * If m_Snake_moving m_MyFrame_right, raises the m_MyFrame_x co-ordinate by the speed of the snake
			 */

		}

		/**
		 * draw Method
		 * Used to draw the snake
		 * @param g
		 */
		@Override
		public void draw(Graphics g)
		{
			/**
			 * Checks to see if the snake is in bounds using outofBounds method
			 * Removes part(s) of the body if necessary, using eatBody method
			 */
			outofBounds();
			eatBody();

			/**
			 * Adds a new point to the List of body points
			 */
			m_MyFrame_bodyPoints.add(new Point(m_MyFrame_x, m_MyFrame_y));

			/**
			 * Checks to see if the bodypoints list is bigger than the snake, if so removes a point from the list
			 * Draws the head of the snake at co-ordinates m_MyFrame_x m_MyFrame_y
			 * Calls the drawBody method to draw the body
			 */
			if (m_MyFrame_bodyPoints.size() == (this.m_MyFrame_length + 1) * m_MyFrame_num)
			{
				m_MyFrame_bodyPoints.remove(0);
			}
			g.drawImage(m_MyFrame_newImgSnakeHead, m_MyFrame_x, m_MyFrame_y, null);
			drawBody(g);


			move();
		}

		/**
		 * eatBody Method
		 * Removes parts of the body that should no longer be there
		 */
		public void eatBody()
		{
			for (Point point : m_MyFrame_bodyPoints)
			{
				for (Point point2 : m_MyFrame_bodyPoints)
				{
					if (point.equals(point2) && point != point2)
					{
						this.m_MyFrame_l = false;
					}
				}
			}
		}

		/**
		 * drawBody Method
		 * Used to draw the body of the snake
		 * @param g
		 */
		public void drawBody(Graphics g)
		{
			/**
			 * Creates variable m_MyFrame_length and assigns the m_MyFrame_length of the body to it
			 */
			int length = m_MyFrame_bodyPoints.size() - 1 - m_MyFrame_num;

			/**
			 * Loops through all body points and draws them
			 */
			for (int i = length; i >= m_MyFrame_num; i -= m_MyFrame_num)
			{
				Point point = m_MyFrame_bodyPoints.get(i);
				g.drawImage(this.m_MyFrame_i, point.x, point.y, null);
			}
		}

		/**
		 * outofBounds Method
		 * Checks to see if the snake is still in bounds
		 */
		private void outofBounds()
		{
			/**
			 * Creates variables m_MyFrame_xOut and m_MyFrame_yOut
			 * Assigns the values that cause the snake to be out of bounds
			 * Will be true if the requirements are met
			 */
			boolean m_MyFrame_xOut = (m_MyFrame_x <= 0 || m_MyFrame_x >= (870 - m_MyFrame_w));
			boolean m_MyFrame_yOut = (m_MyFrame_y <= 40 || m_MyFrame_y >= (560 - m_MyFrame_h));
			/**
			 * Checks to see if m_MyFrame_xOut or m_MyFrame_yOut are true
			 * If they are, snake is no longer visible, game ends
			 */
			if (m_MyFrame_xOut || m_MyFrame_yOut)
			{
				m_MyFrame_l = false;
			}
		}
	}

	/**
	 * SnakeObject Class
	 * The snake itself
	 * Has all the variables for the snake
	 */
	public abstract static class SnakeObject
	{
		/**
		 * Creates variables m_MyFrame_x, m_MyFrame_y, m_MyFrame_i, m_MyFrame_w, m_MyFrame_h and m_MyFrame_l
		 * m_MyFrame_x and m_MyFrame_y being the co-ordinates
		 * m_MyFrame_w and m_MyFrame_h being the width and height
		 * m_MyFrame_i being the image
		 * m_MyFrame_l being whether the snake is alive or dead
		 */
		int m_MyFrame_x;
		int m_MyFrame_y;
		Image m_MyFrame_i;
		int m_MyFrame_w;
		int m_MyFrame_h;

		public boolean m_MyFrame_l;

		/**
		 * draw Method
		 * @param g
		 */
		public abstract void draw(Graphics g);

		/**
		 * getRectangle Method
		 * Returns a new Rectangle at co-ordinates m_MyFrame_x m_MyFrame_y, of width m_MyFrame_w and height m_MyFrame_h
		 * @return new Rectangle
		 */
		public Rectangle getRectangle()
		{
			return new Rectangle(m_MyFrame_x, m_MyFrame_y, m_MyFrame_w, m_MyFrame_h);
		}
	}
}
