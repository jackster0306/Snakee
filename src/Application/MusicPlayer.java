package Application;


import java.io.File;

import javafx.scene.media.MediaPlayer;

import javafx.scene.media.Media;

public class MusicPlayer extends Thread
{
	Media media;
	MediaPlayer player;
	/**
	 * MusicPlayer method
	 * Changes the local variable filename to the given filename when the method is called
	 * @param filename - the name of the file to be played
	 */
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