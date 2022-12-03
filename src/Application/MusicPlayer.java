package Application;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer extends Thread
{
	//Class Variables
	Media m_media;
	MediaPlayer m_player;

	/**
	 * Plays the Music in the given file and loops it to play again when it ends
	 * @param filename the file path where the music file is located
	 */
	public MusicPlayer(String filename)
	{
		m_media = new Media(new File(filename).toURI().toString());
		m_player = new MediaPlayer(m_media);
		Runnable loop = new Runnable() {
			@Override
			public void run() {
				m_player.dispose();
				m_player = new MediaPlayer(m_media);
				m_player.play();
				m_player.setOnEndOfMedia(this);
			}
		};
		m_player.setOnEndOfMedia(loop);
		m_player.play();
	}
}