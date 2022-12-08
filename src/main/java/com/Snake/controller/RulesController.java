package com.Snake.controller;

import com.Snake.Main;

import java.io.IOException;

/**
 * Controls the Rules Screen
 * @author Jack Gribble
 */
public class RulesController {
    /**
     * Loads the Start Screen
     * Called when the 'back' button is pressed
     * @throws IOException
     */
    public void Back() throws IOException {
        //MusicPlayer.StopMusic();
       Main.SetRoot("StartScreen");
    }
}
