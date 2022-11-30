package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EndScreenController {
    @FXML
    private Label endsclabel;

    String level;

    String diffbomb;
    public void initialize() throws IOException {
        endsclabel.setText((PlayScreenController.GetScore()));

        level = CheckLvl();
        diffbomb = CheckDiffBomb();

        File file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Resources\\Lvl"+level+diffbomb+".txt");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            scanner.next();
        }
        FileWriter writer = new FileWriter(file, true);
        writer.write(StartScreenController.GetPlayerName()+"\n"+PlayScreenController.GetScore()+"\n");
        writer.close();

    }
    public void Restart() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }

    public void Exit(){
        System.exit(0);
    }

    public String CheckDiffBomb(){
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

    public String CheckLvl(){
        return Integer.toString(StartScreenController.GetLevel());
    }
}