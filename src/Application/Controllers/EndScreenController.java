package Application.Controllers;

import Application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controls the End Screen
 */
public class EndScreenController {
    //FXML Variables
    @FXML
    private Label endsclabel;

    /**
     * Contains everything to do when the End Screen is loaded
     * Sets a label to the score from the game that just ended
     * Gets the level and difficulty of the game that just ended
     * Opens the relevant file based on the difficulty and level of the game played
     * Adds the player name and score of the game to this file
     * @throws IOException
     */
    public void initialize() throws IOException {
        endsclabel.setText((PlayScreenController.GetScore()));
        String diffbomb = checkDiffBomb();
        File file = new File("src/Resources/TextFiles/"+ diffbomb +".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            scanner.next();
        }
        FileWriter writer = new FileWriter(file, true);
        writer.write(StartScreenController.GetPlayerName()+"\n"+PlayScreenController.GetScore()+"\n");
        writer.close();

    }

    /**
     * Called when the 'Play Again' button is pressed
     * Sets the root of the program to the Start Screen
     * @throws IOException
     */
    public void Restart() throws IOException {
        Main.SetRoot("StartScreen");
    }

    /**
     * Called when the 'Exit' button is pressed
     * Exits the program with exit code 0
     */
    public void Exit(){
        System.exit(0);
    }

    /**
     * Gets the difficulty of the game just played and whether bombs were activated.
     * Returns a String depending on the difficulty and whether bombs were activated.
     * @return a string that correlates to part of the name of a text file
     */
    private String checkDiffBomb(){
            int diff = StartScreenController.GetDiff();
            boolean bombs = StartScreenController.GetToBomb();
            if(diff == 1 && !bombs){
                return "Easy";
            } else if(diff == 2 && !bombs){
                return "Med";
            } else if(diff == 3 && !bombs){
                return "Hard";
            } else if(diff == 1){
                return "EasyBombs";
            } else if(diff == 2){
                return "MedBombs";
            } else{
                return "HardBombs";
            }
    }

}