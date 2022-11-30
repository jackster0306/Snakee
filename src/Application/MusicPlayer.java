package Application;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer extends Thread
{
	Media media;
	MediaPlayer player;
	public MusicPlayer(String filename)
	{
		media = new Media(new File(filename).toURI().toString());
		player = new MediaPlayer(media);
		Runnable loop = new Runnable() {
			@Override
			public void run() {
				player.dispose();
				player = new MediaPlayer(media);
				player.play();
				player.setOnEndOfMedia(this);
			}
		};
		player.setOnEndOfMedia(loop);
		player.play();
	}
}