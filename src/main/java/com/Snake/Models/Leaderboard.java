package com.Snake.Models;

import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays a Leaderboard
 * @author Jack Gribble
 */
public class Leaderboard {
    /**
     * Sets up the Leaderboard and displays it to the user
     * @param diff a String correlating to part of a TextFile
     * @param NamesArea the Text Area to display the names in
     * @param ScoresArea the Text Area to display the scores in
     */
    public Leaderboard(String diff, TextArea NamesArea, TextArea ScoresArea) {
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
        File file = new File("src/main/resources/com/Snake/TextFiles/" + diff +".txt");
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

    /**
     * Sorts the 2 Lists to display highest score first with the name of who got that score
     * Ready for the Leaderboard to be displayed
     * @param scores the ArrayList of scores
     * @param names the ArrayList of names
     */
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
}
