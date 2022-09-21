package model;

import model.gamestates.*;
import model.mapclasses.TerrainBorder;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;
import model.gameobjects.Player;
import view.buttons.GameButton;

import model.gameobjects.Projectile;
import model.gameobjects.enemies.IEnemy;
import view.IObserver;
import view.buttons.buttonactions.MenuButtonAction;
import view.buttons.buttonactions.QuitButtonAction;
import view.panelstates.EPanelState;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game {
    private ArrayList<IObserver> observers;
    private IGameState state;
    private Player player;
    private ArrayList<String> highscoreName;
    private ArrayList<IEnemy> enemies;
    private ArrayList<Projectile> projectiles;
    private TerrainBorder terrainBorder;

    private ArrayList<GameButton> mainMenuButtons, backButtons, pauseButtons;

    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed, spacePressed;

    private boolean stateChangedFlag;

    private GameMap gameMap;
    private File highscoreFile;
    private ArrayList<String> highscoreList;

    private Game() {}

    private static Game game;
    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }return game;
    }

    public void createGame(){
        player = new Player(32, 32, 0.5, 1000);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        terrainBorder = new TerrainBorder(960, 800);
        highscoreName = new ArrayList<>();
        this.gameMap = new GameMap();
        initMainMenuButtons();
        initBackButtons();
        initPauseButtons();

        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        enterPressed = false;
        escapePressed = false;
        spacePressed = false;

        stateChangedFlag = false;

        state = new MainMenuState();
        state.updateButtons();
        observers = new ArrayList<>();
        highscoreFile = new File("textfiles/highscores.txt");
        highscoreList = getHighScoreList();
    }


    public ArrayList<String> getHighScoreList() {
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

    public ArrayList<String> getHighscoreName(){
        return highscoreName;
    }

    public boolean isTopFive(){
        int oldScore;
        for (String playerScore : highscoreList){
            oldScore = Integer.valueOf(playerScore.split(":")[1]);
            if (player.getScore() >= oldScore){
                return true;
            }
        }
        return false;
    }

    /**
     * Handles end of the game, either new highscore or back to menu.
     */
    public void gameOver(){

        if (isTopFive()){
            //NEW HIGHSCORE state
        }else{
            //GAME OVER MENU
        }

    }

    /**
     * Saves the new highscore to a textfile.
     * @param list List of highscores
     */

    private void saveHighscore(ArrayList<String> list){
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


    /**
     * Updates the list containing highscores.
     */
    public void updateHighscoreList(){
        int i = 0;
        if (highscoreList.isEmpty()){
            highscoreList.add(String.join("", highscoreName) + ":" + player.getScore());
        }else {
            for (String playerScore : highscoreList) {
                String[] savedScore = playerScore.split(":");
                int score = Integer.valueOf(savedScore[1]);
                if (player.getScore() >= score) {
                    highscoreList.add(i, String.join("", highscoreName) + ":" + player.getScore());
                    break;
                }
                    i++;
                if (i == highscoreList.size()){
                    highscoreList.add(String.join("", highscoreName) + ":" + player.getScore());
                    break;
                }
            }
        }
        saveHighscore(highscoreList);
    }


    public void deleteLetter(){
        if (highscoreName.size() > 0){
            highscoreName.remove(highscoreName.size()-1);
        }
    }

    public void updateName(String letter){
        if (highscoreName.size() < 6) {
            highscoreName.add(letter);
        }
    }

    /**
     * Returns the instance of the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
    public ArrayList<IEnemy> getEnemies(){
        return enemies;
    }
    public ArrayList<Projectile> getProjectiles() { return projectiles; }
    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All Game Map Tiles
     */
    public ArrayList<IGameObject> getTerrainBorder() {
        return terrainBorder.getTerrainBorder();
    }

    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All Game Map Tiles
     */
    public HashMap<String, Terrain> getGrass() {
        return gameMap.getGrass();
    }

    public ArrayList<Terrain> getPath(){
        return gameMap.getPath();
    }
    public void setPath(ArrayList<Terrain> path){
        gameMap.setPath(path);
    }

    /**
     * Returns an ArrayList containing all the buttons for the main menu.
     * @return  Main Menu Buttons
     */
    public ArrayList<GameButton> getMainMenuButtons() {
        return mainMenuButtons;
    }

    public ArrayList<GameButton> getBackButtons(){return backButtons;}

    public ArrayList<GameButton> getPauseButtons(){
        return pauseButtons;
    }

    /**
     * Used as a way for outside components to tell Game if the W key is pressed.
     */
    public void setWPressed() {
        wPressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the W key is released.
     */
    public void resetWPressed() {
        wPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the S key is pressed.
     */
    public void setSPressed() {
        sPressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the S key is released.
     */
    public void resetSPressed() {
        sPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the Enter key is pressed.
     */
    public void setEnterPressed() {
        enterPressed = true;
    }

    public void setSpacePressed() {
        spacePressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the Enter key is released.
     */
    public void resetEnterPressed() {
        enterPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the Escape key is pressed.
     */
    public void setEscapePressed() {
        escapePressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the Escape key is released.
     */
    public void resetEscapePressed() {
        escapePressed = false;
    }

    /**
     * Used as a way for objects inside to read whether the W key is pressed
     * @return wPressed
     */
    public boolean getWPressed() {
        return wPressed;
    }

    public boolean getSPressed() {
        return sPressed;
    }

    public boolean getEnterPressed() {
        return enterPressed;
    }

    public boolean getEscapePressed() {
        return escapePressed;
    }

    public boolean getSpacePressed() {
        return spacePressed;
    }

    public boolean readStateChangedFlag() {
        return stateChangedFlag;
    }

    public void resetStateChangedFlag() {
        stateChangedFlag = false;
    }

    /**
     * Use to change the current IGameState
     * @param state an instance of a class that has implemented the IGameState interface
     */
    public void setState(IGameState state) {
        this.state = state;
        state.updateButtons();
        stateChangedFlag = true;
    }

    /**
     * Returns a string containing a unique tag used for identifying the different game states from outside sources.
     * @return StateTag
     */
    public EPanelState getStateTag() {
        return state.getStateTag();
    }


    /**
     * Add an observer. Observers will be notified 120 times per second
     * @param observer observer
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Updates the current IGameState
     */
    public void update() {
        state.update();
    }

    /**
     * Notifies potential observers
     */
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.draw();
        }
    }

    private void initBackButtons(){
        GameButton backButton1 = new GameButton("BACK", 325, 575, new MenuButtonAction(new MainMenuState()));
        backButton1.setIsSelected(true);
        backButtons = new ArrayList<>();
        backButtons.add(backButton1);
    }

    /**
     * Initializes the buttons used in the main menu and stores them in an ArrayList
     */
    private void initMainMenuButtons() {
        GameButton mainMenuButton1 = new GameButton("PLAY", 325, 200, new MenuButtonAction(new InGameState()));
        GameButton mainMenuButton2 = new GameButton("HIGHSCORES", 325, 300, new MenuButtonAction(new HighscoreState()));
        GameButton mainMenuButton3 = new GameButton("HOW TO PLAY", 325, 400, new MenuButtonAction(new HowToPlayState()));
        GameButton mainMenuButton4 = new GameButton("QUIT", 325, 500, new QuitButtonAction());
        mainMenuButtons = new ArrayList<>();
        mainMenuButtons.add(mainMenuButton1);
        mainMenuButtons.add(mainMenuButton2);
        mainMenuButtons.add(mainMenuButton3);
        mainMenuButtons.add(mainMenuButton4);
    }


    private void initPauseButtons(){
        GameButton pauseButton1 = new GameButton("RESUME", 325, 200, new MenuButtonAction(new InGameState()));
        GameButton pauseButton2 = new GameButton("RESTART", 325, 300, new MenuButtonAction(new InGameState()));
        GameButton pauseButton3 = new GameButton("MAIN MENU", 325, 400, new MenuButtonAction(new MainMenuState()));
        pauseButtons = new ArrayList<>();
        pauseButtons.add(pauseButton1);
        pauseButtons.add(pauseButton2);
        pauseButtons.add(pauseButton3);

    }


    public void resetSpacePressed() {
        this.spacePressed = false;
    }
}
