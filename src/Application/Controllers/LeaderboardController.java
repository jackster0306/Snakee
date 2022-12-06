package Application.Controllers;

import Application.StartScreenJFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controls the Leaderboard class
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
    private String m_level = "1";
    private String m_diffbomb = "Easy";
    private ObservableList<String> m_tablechoices = FXCollections.observableArrayList("No Bombs Easy", "No Bombs Medium", "No Bombs Hard","Bombs Easy","Bombs Medium","Bombs Hard");

    /**
     * Sets up the Leaderboard Screen when it gets loaded
     * Contains everything to do when the Leaderboard Screen is loaded
     * Sets up the choice boxes to display the right information
     */
    public void initialize(){
        selecttable.setItems(m_tablechoices);
        selecttable.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            m_diffbomb = checkDiffBombs(newval.intValue());
            setupLeaderboard();
        });
        selecttable.setValue("No Bombs Easy");
    }

    /**
     * Sets up the Leaderboard and displays it to the user
     */
    private void setupLeaderboard() {
        NamesArea.setEditable(true);
        ScoresArea.setEditable(true);
        int list = 1;
        int i;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();
        NamesArea.clear();
        NamesArea.appendText("Name\n");
        ScoresArea.clear();
        ScoresArea.appendText("Score\n");
        File file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Resources\\TextFiles\\" + m_diffbomb +".txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                if(list %2 == 0) {
                    scores.add(Integer.parseInt(scanner.nextLine()));
                } else{
                    names.add(scanner.nextLine());
                }
                list++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        sortLists(scores, names);

        NamesArea.setEditable(false);
        ScoresArea.setEditable(false);
        int num = names.size();
        if(names.size() > 4){
            num = 4;
        }
        for(i = 0; i < num; i++){
            if(i == 3){
                NamesArea.appendText(names.get(i));
                ScoresArea.appendText(Integer.toString(scores.get(i)));
            } else{
                NamesArea.appendText(names.get(i)+"\n\n");
                ScoresArea.appendText(scores.get(i)+"\n\n");
            }
        }
    }

    private void sortLists(ArrayList<Integer> scores, ArrayList<String> names){
        int i;
        int j;
        for(i = 0; i < scores.size(); i++){
            for(j = i; j < scores.size(); j++){
                if(scores.get(j) > scores.get(i)){
                    int smaller = scores.get(i);
                    int larger = scores.get(j);
                    scores.remove(i);
                    scores.add(i,larger);
                    scores.remove(j);
                    scores.add(j, smaller);
                    String smallst = names.get(i);
                    String largerst = names.get(j);
                    names.remove(i);
                    names.add(i,largerst);
                    names.remove(j);
                    names.add(j, smallst);
                }
            }
        }
    }
    public void ToStartScreen() throws IOException {
        StartScreenJFX.SetRoot("StartScreen");}

    private String checkDiffBombs(int num){
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