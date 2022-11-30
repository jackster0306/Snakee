package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LeaderboardController {
    public String level = "1";

    public String diffbomb = "Easy";

    ObservableList<String> tablechoices = FXCollections.observableArrayList("No Bombs Easy", "No Bombs Medium", "No Bombs Hard","Bombs Easy","Bombs Medium","Bombs Hard");
    ObservableList<String> levels = FXCollections.observableArrayList("1", "2", "3");

    @FXML
    private TableView leaderboard;

    @FXML
    private TableColumn NameCol;

    @FXML
    private TableColumn ScoreCol;

    @FXML
    private ChoiceBox selecttable;

    @FXML
    private ChoiceBox lvlchoice;

    @FXML
    private TextArea textArea;

    public void initialize(){
        selecttable.setItems(tablechoices);
        lvlchoice.setItems(levels);
        selecttable.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            diffbomb = CheckDiffBombs(newval.intValue());
            SetupLeaderboard();
        });
        lvlchoice.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            level = Integer.toString(newval.intValue() + 1);
            SetupLeaderboard();
        });
        lvlchoice.setValue("1");
        selecttable.setValue("No Bombs Easy");
    }


    public void SetupLeaderboard() {
        textArea.clear();
        File file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Resources\\Lvl"+level+diffbomb+".txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                textArea.appendText(scanner.nextLine());
                textArea.appendText("\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    public void ToStartScreen() throws IOException {

        StartScreenJFX.setRoot("StartScreen");
    }

    public String CheckDiffBombs(int num){
        if(num == 0){
            return "Easy";
        } else if(num == 1){
            return "Med";
        } else if(num == 2){
            return "Hard";
        } else if(num == 3){
            return "EasyBombs";
        } else if(num == 4){
            return "MedBombs";
        } else{
            return "HardBombs";
        }
    }
}
