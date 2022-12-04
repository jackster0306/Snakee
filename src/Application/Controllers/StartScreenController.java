package Application.Controllers;


import Application.StartScreenJFX;
import Application.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Controls the Start Screen
 * Used to set up all the variables ready for the game to be played
 */
public class StartScreenController {
    //FXML Variables
    @FXML
    private ComboBox scorechoice;
    @FXML
    private ComboBox diffchoice;
    @FXML
    private ComboBox bgchoice;
    @FXML
    private CheckBox checkbomb;
    @FXML
    private TextField name;
    @FXML
    private ComboBox lvlchoice;

    //Class Variables
    ObservableList<String> m_colours = FXCollections.observableArrayList("Red", "Yellow", "Magenta","Green","Orange");
    ObservableList<String> m_difficulties = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    ObservableList<String> m_backgrounds = FXCollections.observableArrayList("Snake", "Basketball", "Football");
    ObservableList<Integer> m_levels = FXCollections.observableArrayList(1, 2, 3);
    private static boolean m_bombs = false;
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
    private int thetheme = 0;

    //Getters
    public static boolean GetToBomb(){
        return m_bombs;
    }
    public static int GetLevel(){
        return m_level;
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

    //Setters
    public void SetName(){
        m_playername = name.getText();
    }


    /**
     * Sets up the Start Screen when it gets loaded
     * Contains everything to do when the Start Screen is loaded
     * Sets up all the components in the screen to have the default values
     * Sets up the combo boxes with the necessary values
     */
    public void initialize() {
        m_diff = 1;
        m_level = 1;
        SetupComboBox(scorechoice, m_colours, "Score Colour");
        SetupComboBox(diffchoice, m_difficulties, "Difficulty");
        SetupComboBox(lvlchoice, m_levels, "Level");
        SetupComboBox(bgchoice, m_backgrounds, "Theme");
        diffchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_diff = newval.intValue() + 1);
        scorechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_scorecol = CheckCol(newval.intValue()));
        bgchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> thetheme = newval.intValue());
        lvlchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_level = newval.intValue() + 1);
    }

    /**
     * Returns the colour related to the integer provided
     * @param col the integer that relates to a colour
     * @return a String which is the colour that relates to the integer provided
     */
    public String CheckCol(int col){
        switch(col){
            case 0: return "red";
            case 1: return "yellow";
            case 2: return "magenta";
            case 3: return "green";
            default: return "orange";
        }
    }

    /**
     * Loads the Play Screen (Starts the game)
     * Checks to see if a name has been entered
     * If not, it lets the user know and the game is not started
     * Sets the theme
     * If name is entered, checks the difficulty and loads the correct Screen accordingly
     * @throws IOException
     */
    @FXML
    private void PlayGame() throws IOException {
        if(m_playername == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("You must enter a name");
            a.show();
        }
        else{
            new Theme(thetheme);
            m_bombs = checkbomb.isSelected();
            switch(m_diff){
                case 1:
                    StartScreenJFX.setRoot("PlayScreen");
                    break;
                case 2:
                    StartScreenJFX.setRoot("PlayScreenMedium");
                    break;
                case 3: StartScreenJFX.setRoot("PlayScreenHard");
            }
        }
    }

    /**
     * Loads the rules screen
     * @throws IOException
     */
    public void ShowRules() throws IOException {
        StartScreenJFX.setRoot("RulesScreen");
    }

    /**
     * Loads the Leaderboard screen
     * @throws IOException
     */
    public void ShowLeaderboard() throws IOException {
        StartScreenJFX.setRoot("LeaderboardScreen");
    }

    /**
     * Sets up a ComboBox with the provided parameters
     * @param cb the ComboBox to be setup
     * @param ol the ObservableList containing the items to be put in the ComboBox
     * @param st the String to set the value of the ComboBox to
     */
    public void SetupComboBox(ComboBox cb, ObservableList ol, String st){
        cb.setItems(ol);
        cb.setValue(st);
    }
}