package Application.Controllers;


import Application.MusicPlayer;
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

    //Class Variables
    private ObservableList<String> m_colours = FXCollections.observableArrayList("Red", "Yellow", "Magenta","Green","Orange");
    private ObservableList<String> m_difficulties = FXCollections.observableArrayList("Easy", "Hard");
    private ObservableList<String> m_backgrounds = FXCollections.observableArrayList("Snake", "Basketball", "Football");
    private static boolean m_bombs = false;
    private static String m_scorecol = "magenta";
    public static String GetScoreCol(){
        return m_scorecol;
    }
    private static int m_diff = 1;
    private static String m_playername;
    private int thetheme = 0;

    //Getters
    public static int GetDiff() {return m_diff;}
    public static String GetPlayerName(){
        return m_playername;
    }
    public static boolean GetToBomb(){
        return m_bombs;
    }

    /**
     * Sets up the Start Screen when it gets loaded
     * Contains everything to do when the Start Screen is loaded
     * Sets up all the components in the screen to have the default values
     * Sets up the combo boxes with the necessary values
     */
    public void initialize() {
        new MusicPlayer("src/Resources/Music/frogger.mp3", true);
        m_diff = 1;
        setupComboBox(scorechoice, m_colours, "Score Colour");
        setupComboBox(diffchoice, m_difficulties, "Difficulty");
        setupComboBox(bgchoice, m_backgrounds, "Theme");
        diffchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_diff = newval.intValue() + 1);
        scorechoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> m_scorecol = checkCol(newval.intValue()));
        bgchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> thetheme = newval.intValue());
    }

    /**
     * Returns the colour related to the integer provided
     * @param col the integer that relates to a colour
     * @return a String which is the colour that relates to the integer provided
     */
    private String checkCol(int col){
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
    private void playGame() throws IOException {
        if(name.getText() == ""){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("You must enter a name");
            a.show();
        }
        else{
            m_playername = name.getText();
            new Theme(thetheme);
            MusicPlayer.StopMusic();
            m_bombs = checkbomb.isSelected();
            switch(m_diff){
                case 1:
                    StartScreenJFX.SetRoot("PlayScreen");
                    break;
                case 2:
                    StartScreenJFX.SetRoot("PlayScreenMedium");
                    break;
            }
        }
    }

    /**
     * Loads the rules screen
     * @throws IOException
     */
    public void ShowRules() throws IOException {
        StartScreenJFX.SetRoot("RulesScreen");
    }

    /**
     * Loads the Leaderboard screen
     * @throws IOException
     */
    public void ShowLeaderboard() throws IOException {
        StartScreenJFX.SetRoot("LeaderboardScreen");
    }

    /**
     * Sets up a ComboBox with the provided parameters
     * @param cb the ComboBox to be setup
     * @param ol the ObservableList containing the items to be put in the ComboBox
     * @param st the String to set the value of the ComboBox to
     */
    private void setupComboBox(ComboBox cb, ObservableList ol, String st){
        cb.setItems(ol);
        cb.setValue(st);
    }
}