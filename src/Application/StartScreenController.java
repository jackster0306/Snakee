package Application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;



public class StartScreenController {
    @FXML
    private ChoiceBox scorechoice;

    @FXML
    private ChoiceBox snakechoice;

    @FXML
    private ChoiceBox diffchoice;

    @FXML
    private ChoiceBox bgchoice;

    @FXML
    private CheckBox checkbomb;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox lvlchoice;
    ObservableList<String> m_colours = FXCollections.observableArrayList("Red", "Yellow", "Magenta","Green","Orange");

    ObservableList<String> m_difficulties = FXCollections.observableArrayList("Easy", "Medium", "Hard");

    ObservableList<String> m_backgrounds = FXCollections.observableArrayList("Sky", "Cartoon Sky");

    ObservableList<Integer> m_levels = FXCollections.observableArrayList(1, 2, 3);
    private static boolean m_bombs = false;

    public static boolean GetToBomb(){
        return m_bombs;
    }


    static String m_background = "PlayPaneSky";

    public static String GetBackground(){
        return m_background;
    }

    static String m_scorecol = "magenta";

    static String m_snakecol = "green";

    public static String GetScoreCol(){
        return m_scorecol;
    }

    static int m_diff = 1;

    static int m_level = 1;

    public static int GetDiff(){
        return m_diff;
    }

    private static String m_playername;

    public static String GetPlayerName(){
        return m_playername;
    }





    private static int m_chosen;

    public static int GetChosen(){
        return m_chosen;
    }

    public void initialize() {
        m_chosen = 0;
        m_diff = 1;
        m_level = 1;
        SetChoiceBox(scorechoice, m_colours, "Score Colour");
        SetChoiceBox(snakechoice, m_colours, "Snake Colour");
        SetChoiceBox(diffchoice, m_difficulties, "Difficulty");
        SetChoiceBox(lvlchoice, m_levels, "Level");
        SetChoiceBox(bgchoice, m_backgrounds, "Theme");
        diffchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_diff = newval.intValue() + 1);
        scorechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_scorecol = CheckCol(newval.intValue()));
        snakechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_snakecol = CheckCol(newval.intValue()));
        bgchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            int i = newval.intValue();
            if(i == 0){
                m_background = "PlayPaneSky";
            } else{
                m_background = "PlayPaneCart";
            }
        });
        lvlchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_level = newval.intValue() + 1);
    }

    public String CheckCol(int col){
        switch(col){
            case 0: return "red";
            case 1: return "yellow";
            case 2: return "magenta";
            case 3: return "green";
            default: return "orange";
        }
    }

    @FXML
    private void PlayGame() throws IOException {
        if(m_playername == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("You must enter a name");
            a.show();
        }
        else{
            m_bombs = checkbomb.isSelected();
            if(m_bombs)
                m_chosen += 3;
            if(m_diff == 1){
                StartScreenJFX.setRoot("PlayScreen");
            } else if (m_diff == 2) {
                StartScreenJFX.setRoot("PlayScreenMedium");
            } else if (m_diff == 3) {
                StartScreenJFX.setRoot("PlayScreenHard");
            }

        }
    }

    public static double GetSpeed(){
        if(m_level == 1) {
            return 0.03;
        } else if(m_level == 2){
            return 0.0175;
        } else{
            return 0.01;
        }
    }

    public static int GetLevel(){
        return m_level;
    }


    public void SetName(){
        m_playername = name.getText();
    }

    public void ShowRules() throws IOException {
        StartScreenJFX.setRoot("RulesScreen");
    }

    public void ShowLeaderboard() throws IOException {
        StartScreenJFX.setRoot("LeaderboardScreen");
    }

    public void SetChoiceBox(ChoiceBox cb,ObservableList ol, String st){
        cb.setItems(ol);
        cb.setValue(st);
    }
}