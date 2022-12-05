package Application;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Plays Music in the provided filename
 */
public class MusicPlayer extends Thread
{
	//Class Variables
	private static MediaPlayer m_player;

	/**
	 * Plays the Music in the given file and loops it to play again when it ends
	 * @param filename the file path where the music file is located
	 * @param toloop the boolean that decides whether the music is to be looped or not
	 */
	public MusicPlayer(String filename, boolean toloop)
	{
		Media media = new Media(new File(filename).toURI().toString());
		m_player = new MediaPlayer(media);
		if(toloop){
			Runnable loop = new Runnable() {
				@Override
				public void run() {
					m_player.dispose();
					m_player = new MediaPlayer(media);
					m_player.play();
					m_player.setOnEndOfMedia(this);
				}
			};
			m_player.setOnEndOfMedia(loop);
		}
		m_player.play();
	}

	public static void StopMusic(){
		m_player.stop();
	}
}