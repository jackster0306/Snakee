package example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;
import java.awt.Graphics2D;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread
{
	/**
	 * Creates local variables filename and player
	 */
	private String filename;
	public Player player;


	/**
	 * MusicPlayer method
	 * Changes the local variable filename to the given filename when the method is called
	 * @param filename - the name of the file to be played
	 */
	public MusicPlayer(String filename)
	{
		this.filename = filename;
	}


	/**
	 * play Method
	 * This plays the music file currently in the local variable filename
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
	 * This method plays the music in the given file
	 * @param filename
	 */
	public static void getMusicPlay(String filename)
	{
		/**
		 * getMusicPlay method
		 * This method plays the music in the given file
		 * @param filename
		 */
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
