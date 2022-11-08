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
	 * This class is the Main window when the game is run, it is what the game is played on
	 * Constantly refreshed
	 */

	private static final long m_MyFrame_serialVersionUID = -3149926831770554380L;

	public JFrame m_MyFrame_jFrame = new JFrame();
	/**
	 * Creates varaible m_MyFrame_jFrame of type JFrame
	 */

	public MyFrame()
	{
		m_MyFrame_jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("snake-logo.png")));

	}

	public void LoadFrame()
	{
		/**
		 * LoadFrame method
		 * Sets up the frame ready to be used
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
		private int speed_XY;
		private int length;
		private int num; // ?
		public int score = 0;
		/**
		 * Creates the initial variables for the speed and length of the snake
		 * Creates a variable num
		 * Creates variable score, used for the score, and sets it to 0
		 */

		private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.m_ImageUtil_images.get("snake-head-right");
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

		boolean up, down, left, right = true;
		/**
		 * Creates boolean variables for up, down, left and right. Sets them all to true
		 */

		public MySnake(int x, int y)
		{
			this.l = true;
			this.x = x;
			this.y = y;
			this.i = ImageUtil.m_ImageUtil_images.get("snake-body");
			this.w = i.getWidth(null);
			this.h = i.getHeight(null);
			/**
			 * Sets the co-ordinates of the snake
			 * Adds image for the body of the snake
			 * Gets the width and height of the image and changes the variables w and h, used for width and height, to these values
			 */

			this.speed_XY = 5;
			this.length = 1;
			/**
			 * Sets the speed and length of the snake
			 */

			/*
			 * Attention : ?
			 */
			this.num = w / speed_XY;
			m_MyFrame_newImgSnakeHead = IMG_SNAKE_HEAD;

		}

		/**
		 * GetLength method
		 * Returns the length of the snake
		 * @return length
		 */
		public int GetLength()
		{
			return length;
		}

		/**
		 * ChangeLength method
		 * Changes the length of the snake
		 * @param length - length to change local variable length of the snake to
		 */
		public void ChangeLength(int length)
		{
			this.length = length;
		}

		/**
		 * KeyPressed method
		 * Used for when a key is pressed
		 * @param e
		 */
		public void KeyPressed(KeyEvent e)
		{
			// Check the key
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				if (!down)
				{
					up = true;
					down = false;
					left = false;
					right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.RotateImage(IMG_SNAKE_HEAD, -90);
				}
				break;
				/**
				 * Case for if the UP key is pressed
				 * Changes the values of down, left and right to false
				 * Changes value of up to true
				 * Does this because the snake will now be m_Snake_moving up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face up
				 */

			case KeyEvent.VK_DOWN:
				if (!up)
				{
					up = false;
					down = true;
					left = false;
					right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.RotateImage(IMG_SNAKE_HEAD, 90);
				}
				break;
				/**
				 * Case for if the DOWN key is pressed
				 * Changes the values of up, left and right to false
				 * Changes value of down to true
				 * Does this because the snake will now be m_Snake_moving up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face down
				 */

			case KeyEvent.VK_LEFT:
				if (!right)
				{
					up = false;
					down = false;
					left = true;
					right = false;

					m_MyFrame_newImgSnakeHead = (BufferedImage) GameUtil.RotateImage(IMG_SNAKE_HEAD, -180);

				}
				break;
				/**
				 * Case for if the LEFT key is pressed
				 * Changes the values of up, down and right to false
				 * Changes value of left to true
				 * Does this because the snake will now be m_Snake_moving up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face left
				 */

			case KeyEvent.VK_RIGHT:
				if (!left)
				{
					up = false;
					down = false;
					left = false;
					right = true;

					m_MyFrame_newImgSnakeHead = IMG_SNAKE_HEAD;
				}

			default:
				break;
				/**
				 * Case for if the RIGHT key is pressed
				 * Changes the values of up, down and left to false
				 * Changes value of right to true
				 * Does this because the snake will now be m_Snake_moving up and these values keep track of which way the snake is m_Snake_moving
				 * Rotates the snake head image to face right
				 */
			}
		}


		public void move()
		{
			// Let the swarm move
			if (up)
			{
				y -= speed_XY;
			} else if (down)
			{
				y += speed_XY;
			} else if (left)
			{
				x -= speed_XY;
			} else if (right)
			{
				x += speed_XY;
			}
			/**
			 * Uses the boolean variables up, down, left and right to see which way the snake is m_Snake_moving
			 * If m_Snake_moving up, lowers the y co-ordinate by the speed of the snake
			 * If m_Snake_moving down, raises the y co-ordinate by the speed of the snake
			 * If m_Snake_moving left, lowers the x co-ordinate by the speed of the snake
			 * If m_Snake_moving right, raises the x co-ordinate by the speed of the snake
			 */

		}

		/**
		 * Draw Method
		 * Used to Draw the snake
		 * @param g
		 */
		@Override
		public void Draw(Graphics g)
		{
			/**
			 * Checks to see if the snake is in bounds using OutofBounds method
			 * Removes part(s) of the body if necessary, using EatBody method
			 */
			OutofBounds();
			EatBody();

			/**
			 * Adds a new point to the List of body points
			 */
			m_MyFrame_bodyPoints.add(new Point(x, y));

			/**
			 * Checks to see if the bodypoints list is bigger than the snake, if so removes a point from the list
			 * Draws the head of the snake at co-ordinates x y
			 * Calls the DrawBody method to Draw the body
			 */
			if (m_MyFrame_bodyPoints.size() == (this.length + 1) * num)
			{
				m_MyFrame_bodyPoints.remove(0);
			}
			g.drawImage(m_MyFrame_newImgSnakeHead, x, y, null);
			DrawBody(g);


			move();
		}

		/**
		 * EatBody Method
		 * Removes parts of the body that should no longer be there
		 */
		public void EatBody()
		{
			for (Point point : m_MyFrame_bodyPoints)
			{
				for (Point point2 : m_MyFrame_bodyPoints)
				{
					if (point.equals(point2) && point != point2)
					{
						this.l = false;
					}
				}
			}
		}

		/**
		 * DrawBody Method
		 * Used to Draw the body of the snake
		 * @param g
		 */
		public void DrawBody(Graphics g)
		{
			/**
			 * Creates variable length and assigns the length of the body to it
			 */
			int length = m_MyFrame_bodyPoints.size() - 1 - num;

			/**
			 * Loops through all body points and draws them
			 */
			for (int i = length; i >= num; i -= num)
			{
				Point point = m_MyFrame_bodyPoints.get(i);
				g.drawImage(this.i, point.x, point.y, null);
			}
		}

		/**
		 * OutofBounds Method
		 * Checks to see if the snake is still in bounds
		 */
		private void OutofBounds()
		{
			/**
			 * Creates variables m_MyFrame_xOut and m_MyFrame_yOut
			 * Assigns the values that cause the snake to be out of bounds
			 * Will be true if the requirements are met
			 */
			boolean m_MyFrame_xOut = (x <= 0 || x >= (870 - w));
			boolean m_MyFrame_yOut = (y <= 40 || y >= (560 - h));
			/**
			 * Checks to see if m_MyFrame_xOut or m_MyFrame_yOut are true
			 * If they are, snake is no longer visible, game ends
			 */
			if (m_MyFrame_xOut || m_MyFrame_yOut)
			{
				l = false;
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
		 * Creates variables x, y, i, w, h and l
		 * x and y being the co-ordinates
		 * w and h being the width and height
		 * i being the image
		 * l being whether the snake is alive or dead
		 */
		int x;
		int y;
		Image i;
		int w;
		int h;

		public boolean l;

		/**
		 * Draw Method
		 * @param g
		 */
		public abstract void Draw(Graphics g);

		/**
		 * GetRectangle Method
		 * Returns a new Rectangle at co-ordinates x y, of width w and height h
		 * @return new Rectangle
		 */
		public Rectangle GetRectangle()
		{
			return new Rectangle(x, y, w, h);
		}
	}
}
