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
	 * Creates a variable called mySnake of the type MySnake.
	 * The 2 parameters are the x coordinate and y coordinate of where the snake should spawn.
	 * **/
	public MySnake mySnake = new MySnake(100, 100);// x , y

	/**
	 * Creates variable called food of type Food
	 */
	public Food food = new Food();

	/**
	 * Creates variable called backgroud of type image
	 * Gets the image 'UI-background' that is in the source folder
	 */
	public Image background = ImageUtil.images.get("UI-background");

	/**
	 * Creates variable called fail of type Image for when the user fails the game
	 * Gets the image 'game-scene-01' that is in the source folder
	 */
	public Image fail = ImageUtil.images.get("game-scene-01");

	/**
	 * keyPressed Method
	 * Is used to identify when the user presses a key
	 * @param e - the key event that happened
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		mySnake.keyPressed(e);
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
		 * Adds the background image given to the frame and draws it
		 */
		super.paint(g);
		g.drawImage(background, 0, 0, null);


		// Determine the state of the game.
		/**
		 * if (mySnake.l) determines state of the game. If l is true, game is happening. If l is false, game is not happening
		 * Draws the snake
		 * Checks state of the food. l is true if food is active, false if not
		 * Draws the food
		 * Calls the food eaten to see if the food has been eaten
		 * If l is false, a new food is created
		 * If mySnake.l is false, the image for fail is drawn (game is over)
		 * Calls the drawScore method
		 */
		if (mySnake.l)
		{
			mySnake.draw(g);
			if (food.l)
			{
				food.draw(g);
				food.eaten(mySnake);
			} else
			{
				food = new Food();
			}
		} else
		{
			g.drawImage(fail, 0, 0, null);
		}
		drawScore(g);
	}

	/**
	 * drawScore method
	 * @param g
	 * Is used to display the current score of the game
	 */
	public void drawScore(Graphics g)
	{
		/**
		 * It sets the font, font size, font colour
		 * Displays the text 'SCORE : ' then the current score at the coordinates 20, 40 of the frame
		 */
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + mySnake.score, 20, 40);
	}

	/**
	 * main method
	 * @param args
	 * Starts up the frame
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

		// Play the background music.
		MusicPlayer.getMusicPlay("resource\\music\\background.mp3");
	} 
*/
}
