package model.helperclasses;

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
    /**
     * Returns list of all highscores and related names
     * @return list of highscores
     */

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
     * Saves highscore to file
     * @param name name of player
     * @param newScore score achieved during game
     */
    public void saveHighscore(String name, int newScore){
        int i = 0;
        if (highscoreList.isEmpty()){
            highscoreList.add(name + ":" + newScore);
        }else{
            for (String playerScore : highscoreList) {
                String[] savedScore = playerScore.split(":");
                int score = Integer.valueOf(savedScore[1]);
                if (newScore >= score) {
                    highscoreList.add(i, name + ":" + newScore);
                    break;
                }
                i++;
                if (i == highscoreList.size()){
                    highscoreList.add(name + ":" + newScore);
                    break;
                }
            }

        }

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
