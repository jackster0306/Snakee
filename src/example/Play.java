package example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * 
 * @Project Snakee
 * @Description Play the game
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 

public class Play extends MyFrame
{

	private static final long serialVersionUID = -3641221053272056036L;

	/**
	 * Creates a variable called m_Play_mySnake of the type MySnake.
	 * The 2 parameters are the m_MyFrame_x coordinate and m_MyFrame_y coordinate of where the snake should spawn.
	 * **/
	public MySnake m_Play_mySnake = new MySnake(100, 100);// m_MyFrame_x , m_MyFrame_y

	/**
	 * Creates variable called m_Play_food of type Food
	 */
	public Food m_Play_food = new Food();

	/**
	 * Creates variable called backgroud of type image
	 * Gets the image 'UI-m_Play_background' that is in the source folder
	 */
	public Image m_Play_background = ImageUtil.m_ImageUtil_images.get("UI-m_Play_background");

	/**
	 * Creates variable called m_Play_fail of type Image for when the user fails the game
	 * Gets the image 'game-scene-01' that is in the source folder
	 */
	public Image m_Play_fail = ImageUtil.m_ImageUtil_images.get("game-scene-01");

	/**
	 * keyPressed Method
	 * Is used to identify when the user presses a key
	 * @param e - the key event that happened
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		m_Play_mySnake.keyPressed(e);
	}

	/**
	 * paint method
	 * @param g
	 * It is used to design the frame.
	 */
	@Override
	public void paint(Graphics g)
	{
		/**
		 * Adds the m_Play_background image given to the frame and draws it
		 */
		super.paint(g);
		g.drawImage(m_Play_background, 0, 0, null);


		// Determine the state of the game.
		/**
		 * if (m_Play_mySnake.m_MyFrame_l) determines state of the game. If m_MyFrame_l is true, game is happening. If m_MyFrame_l is false, game is not happening
		 * Draws the snake
		 * Checks state of the m_Play_food. m_MyFrame_l is true if m_Play_food is active, false if not
		 * Draws the m_Play_food
		 * Calls the m_Play_food eaten to see if the m_Play_food has been eaten
		 * If m_MyFrame_l is false, a new m_Play_food is created
		 * If m_Play_mySnake.m_MyFrame_l is false, the image for m_Play_fail is drawn (game is over)
		 * Calls the drawScore method
		 */
		if (m_Play_mySnake.m_MyFrame_l)
		{
			m_Play_mySnake.draw(g);
			if (m_Play_food.m_MyFrame_l)
			{
				m_Play_food.draw(g);
				m_Play_food.eaten(m_Play_mySnake);
			} else
			{
				m_Play_food = new Food();
			}
		} else
		{
			g.drawImage(m_Play_fail, 0, 0, null);
		}
		drawScore(g);
	}

	/**
	 * drawScore method
	 * @param g
	 * Is used to display the current m_MyFrame_score of the game
	 */
	public void drawScore(Graphics g)
	{
		/**
		 * It sets the font, font size, font colour
		 * Displays the text 'SCORE : ' then the current m_MyFrame_score at the coordinates 20, 40 of the frame
		 */
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + m_Play_mySnake.m_MyFrame_score, 20, 40);
	}

	/**
	 * main method
	 * @param args
	 * Starts m_MyFrame_up the frame
	 * Plays the music
	 */
	public static void main(String[] args)
	{
		new Play().loadFrame();
		MusicPlayer.getMusicPlay("src/example/frogger.mp3");

	}
/*	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		// frame.setSize(400,600);
		frame.setBounds(450, 200, 920, 600);
		// frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel panel = new SnakePanel();
		frame.add(panel);

		frame.setVisible(true);

		// Play the m_Play_background music.
		MusicPlayer.getMusicPlay("resource\\music\\m_Play_background.mp3");
	} 
*/
}
