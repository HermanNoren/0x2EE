package helperclasses;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HighscoreHandler {

    private ArrayList<String> highscoreList;

    private File highscoreFile;


    public HighscoreHandler(){
            highscoreFile = new File("textfiles/highscores.txt");
            highscoreList = getHighscoreList();
    }

    public ArrayList<String> getHighscoreList() {
        Scanner sc = null;
        highscoreList = new ArrayList<>();
        try {
            sc = new Scanner(highscoreFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(sc.hasNext()){
            highscoreList.add(sc.next());
        }
        return highscoreList;
    }

    /**
     * Saves the new highscore to a textfile.
     * @param list List of highscores
     */

    public void saveHighscore(ArrayList<String> list){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(highscoreFile, false));
            for (String s : highscoreList) {
                output.write(s + " ");
            }
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }


}
