package Application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread
{
	/**
	 * Creates local variables filename and player
	 */
	private String filename;
	public Player player;

	public String GetFilename(){
		return this.filename;
	}


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
	 * Play Method
	 * This plays the music file currently in the local variable filename
	 */
	public void Play()
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
					//104 characters
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
	 * GetMusicPlay method
	 * This method plays the music in the given file
	 * @param filename
	 */
	public static void GetMusicPlay(String filename)
	{
		/**
		 * GetMusicPlay method
		 * This method plays the music in the given file
		 * @param filename
		 */
		MusicPlayer m_MusicPlayer_musicPlayer = new MusicPlayer(filename);
		m_MusicPlayer_musicPlayer.Play();
	}
}
