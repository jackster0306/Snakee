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


	/**
	 * Can't add a setter for m_MyFrame_serialVersionUID because it is final
	 */
	public final long GetMyFrameSerialVersionUID(){
		return m_MyFrame_serialVersionUID;
	}

	public JFrame jFrame = new JFrame();
	/**
	 * Creates varaible jFrame of type JFrame
	 */

	public MyFrame()
	{
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("snake-logo.png")));
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
		jFrame.add(this);
		jFrame.addKeyListener(this);
		/**
		 * Adds keyListener for jFrame
		 */

		jFrame.setTitle("Snakee Yipee");
		jFrame.setSize(870, 560);
		jFrame.setLocationRelativeTo(null);
		jFrame.addWindowListener(new WindowAdapter()// loka
				/**
				 * Sets title, size and location of the frame
				 * Adds a windowListener to jFrame
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
		jFrame.setVisible(true);
		/**
		 * Sets the jFrame to being visible, so the user can see it
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
			 * Used to update the jFrame
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
			 * Constantly updates the jFrame for the user, every 30 milliseconds
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


}
