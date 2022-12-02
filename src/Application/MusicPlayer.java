package Application;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer extends Thread
{
	Media m_media;
	MediaPlayer m_player;
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