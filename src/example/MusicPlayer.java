package example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;
import java.awt.Graphics2D;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread
{
	private String filename;
	public Player player;

	//MusicPlayer changes the local variable filename to the given filename when the method is called
	public MusicPlayer(String filename)
	{
		this.filename = filename;
	}

	/**
	 * play Method
	 * This plays the given music file
	 * The try catch is used to see if the file exists or not
	 */
	public void play()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				try
				{
					//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
					player = new Player(new BufferedInputStream(new FileInputStream(filename)));
					player.play();

				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.start();
	}

	/**
	 * getMusicPlay method
	 * This method changes the music to the given file
	 * It calls the MusicPlayer method to change the local variable filename to the new file
	 * Finally, it calls the play method to play the given file
	 */
	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
