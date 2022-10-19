package model.helperclasses;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the highscore file, by fetching, writing and saving data from the game.
 */
public class HighscoreHandler {

    private List<String> highscoreList;

    private File highscoreFile;


    public HighscoreHandler(){
            highscoreFile = new File("textfiles/highscores.txt");
            init();
            highscoreList = getHighscoreList();
    }

    /**
     * Creates a new file if it does not already exist.
     */
    private void init(){
        if (highscoreFile.exists()){
        }else{
            try {
                createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Deletes the highscorefile
     */
    public void deleteFile(){
        highscoreFile.delete();
    }

    /**
     * Creates the highscorefile
     * @throws IOException
     */
    public void createNewFile() throws IOException {
        highscoreFile.createNewFile();
    }

    /**
     * Modifies the highscore file to an exact state
     * @param oldVersion data to replace current file
     */
    public void rollBackFile(List<String> oldVersion) {
        writeToFile(oldVersion);
    }

    /**
     * Returns the actual score of a certain saved string containing name and score
     * @param fileitem string to extract score from
     * @return score as an integer
     */
    public int getScore(String fileitem){
        int score = 0;
        if (fileitem.contains(":")) {
            String[] savedScore = fileitem.split(":");
            score = Integer.valueOf(savedScore[1]);
        }
        return score;
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
        return highscoreList;
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
            ex1.printStackTrace();
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
        List<String> topList = highscoreList;
        if (highscoreList.size() > 5) {
            topList = topList.subList(0, 5);
        }
        writeToFile(topList);
    }


}
