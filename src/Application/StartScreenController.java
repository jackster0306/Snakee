package Application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;



public class StartScreenController {
    ObservableList<String> colours = FXCollections.observableArrayList("Red", "Yellow", "Magenta","Green","Orange");

    ObservableList<String> difficulties = FXCollections.observableArrayList("Easy", "Medium", "Hard");

    ObservableList<String> backgrounds = FXCollections.observableArrayList("Sky", "Cartoon Sky");

    ObservableList<Integer> levels = FXCollections.observableArrayList(1, 2, 3);
    private static boolean bombs = false;

    public static boolean GetToBomb(){
        return bombs;
    }


    static String background = "PlayPaneSky";

    public static String GetBackground(){
        return background;
    }

    static String scorecol = "magenta";

    static String snakecol = "green";

    public static String GetScoreCol(){
        return scorecol;
    }

    static int speed = 5;

    static int diff = 1;

    static int level = 1;

    public static int GetDiff(){
        return diff;
    }

    private static String playername;

    public static String GetPlayerName(){
        return playername;
    }

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



    private static int chosen;

    public static int GetChosen(){
        return chosen;
    }

    public void initialize() {
        chosen = 0;
        SetChoiceBox(scorechoice, colours, "Score Colour");
        SetChoiceBox(snakechoice, colours, "Snake Colour");
        SetChoiceBox(diffchoice, difficulties, "Difficulty");
        SetChoiceBox(lvlchoice, levels, "Level");
        SetChoiceBox(bgchoice, backgrounds, "Theme");
        diffchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> diff = newval.intValue() + 1);
        scorechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> scorecol = CheckCol(newval.intValue()));
        snakechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> snakecol = CheckCol(newval.intValue()));
        bgchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            int i = newval.intValue();
            if(i == 0){
                background = "PlayPaneSky";
            } else{
                background = "PlayPaneCart";
            }
        });
        lvlchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> level = newval.intValue() + 1);
    }

    public String CheckCol(int col){
        if(col == 0){
            return "red";
        } else if(col == 1){
            return "yellow";
        } else if(col == 2){
            return "magenta";
        } else if(col == 3){
            return "green";
        } else{
            return "orange";
        }
    }

    @FXML
    private void PlayGame() throws IOException {
        if(playername == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("You must enter a name");
            a.show();
        }
        else{
            bombs = checkbomb.isSelected();
            if(bombs)
                chosen += 3;
            if(diff == 1){
                StartScreenJFX.setRoot("PlayScreen");
            } else if (diff == 2) {
                StartScreenJFX.setRoot("PlayScreenMedium");
            } else if (diff == 3) {
                StartScreenJFX.setRoot("PlayScreenHard");
            }
        }
    }

    public static double GetSpeed(){
        if(level == 1) {
            return 0.03;
        } else if(level == 2){
            return 0.0175;
        } else{
            return 0.01;
        }
    }

    public static int GetLevel(){
        return level;
    }


    public void SetName(){
        playername = name.getText();
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