package com.Snake.Controllers;

import com.Snake.Main;
import com.Snake.Models.Leaderboard;
import com.Snake.Models.MusicPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

/**
 * Controls the Leaderboard class
 * @author Jack Gribble
 */
public class LeaderboardController {
    //FXML Variables
    @FXML
    private ComboBox selecttable;
    @FXML
    private TextArea NamesArea;
    @FXML
    private TextArea ScoresArea;

    //Class Variables
    private String m_diffbomb = "Easy";
    private ObservableList<String> m_tablechoices = FXCollections.observableArrayList("No Bombs Easy", "No Bombs Hard","Bombs Easy","Bombs Hard");

    /**
     * Sets up the Leaderboard Screen when it gets loaded
     * Contains everything to do when the Leaderboard Screen is loaded
     * Sets up the choice boxes to display the right information
     */
    public void initialize(){
        selecttable.setItems(m_tablechoices);
        selecttable.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            m_diffbomb = checkDiffBombs(newval.intValue());
            new Leaderboard(m_diffbomb, NamesArea, ScoresArea);
        });
        selecttable.setValue("No Bombs Easy");
    }


    public void ToStartScreen() throws IOException {
        MusicPlayer.StopMusic();
        Main.SetRoot("StartScreen");
    }

    private String checkDiffBombs(int num){
        if(num == 0){
            return "Easy";
        } else if(num == 1){
            return "Hard";
        } else if(num == 2){
            return "EasyBombs";
        } else{
            return "HardBombs";
        }
    }
}