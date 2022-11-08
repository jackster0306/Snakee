package example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread
{
	/**
	 * Creates local variables m_MusicPlayer_filename and m_MusicPlayer_player
	 */
	private String m_MusicPlayer_filename;
	public Player m_MusicPlayer_player;


	/**
	 * MusicPlayer method
	 * Changes the local variable m_MusicPlayer_filename to the given m_MusicPlayer_filename when the method is called
	 * @param filename - the name of the file to be played
	 */
	public MusicPlayer(String filename)
	{
		this.m_MusicPlayer_filename = filename;
	}


	/**
	 * play Method
	 * This plays the music file currently in the local variable m_MusicPlayer_filename
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
					//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(m_MusicPlayer_filename));
					m_MusicPlayer_player = new Player(new BufferedInputStream(new FileInputStream(m_MusicPlayer_filename)));
					m_MusicPlayer_player.play();

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
		 * @param m_MusicPlayer_filename
		 */
		MusicPlayer m_MusicPlayer_musicPlayer = new MusicPlayer(filename);
		m_MusicPlayer_musicPlayer.play();
	}
}
