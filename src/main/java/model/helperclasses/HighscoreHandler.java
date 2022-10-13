package model.helperclasses;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HighscoreHandler {

    private List<String> highscoreList;

    private File highscoreFile;

    public HighscoreHandler(){
            highscoreFile = new File("textfiles/highscores.txt");
            highscoreList = getHighscoreList();
    }

    /**
     * Clears all content of the highscore file
     */
    public void clearFile(){
        highscoreFile.delete();
        try {
            highscoreFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifies the highscore file to an exact state
     * @param oldVersion data to replace current file
     */
    public void rollBackFile(List<String> oldVersion) {
        clearFile();
        writeToFile(oldVersion);
    }

    /**
     * Returns the actual score of a certain saved string containing name and score
     * @param fileitem string to extract score from
     * @return score as an integer
     */

    public int getScore(String fileitem){
        if (fileitem.contains(":")) {
            String[] savedScore = fileitem.split(":");
            return Integer.valueOf(savedScore[1]);
        }
        return 0;
    }

    /**
     * Returns list of all highscores and related names
     * @return list of highscores
     */

    public List<String> getHighscoreList() {
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
        sc.close();
        return new ArrayList<>(highscoreList);
    }

    /**
     * Writes given data to the highscore-file.
     * @param content - data to be stored in a file
     */

    private void writeToFile(List<String> content){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(highscoreFile, false));
            for (String s : content) {
                output.write(s + " ");
            }
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }

    /**
     * Handles new incoming highscores, by updating and sorting the highscore list.
     * @param name name of player
     * @param newScore score achieved during game
     */
    public void saveHighscore(String name, int newScore) {
        highscoreList = getHighscoreList();
        int i = 0;
        if (highscoreList.isEmpty()) {
            highscoreList.add(name + ":" + newScore);
        } else {
            for (String playerScore : highscoreList) {
                int score = getScore(playerScore);
                if (newScore >= score) {
                    highscoreList.add(i, name + ":" + newScore);
                    break;
                }
                i++;
                if (i == highscoreList.size()) {
                    highscoreList.add(name + ":" + newScore);
                    break;
                }
            }

            }
            writeToFile(highscoreList);

        }


}
