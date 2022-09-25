package model;

import model.gameobjects.*;
import model.gameobjects.enemies.*;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.Entity;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.theShop.Shop;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import view.IObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game{
    private Thread gameLoopThread;
    private final int FPS = 120; // FRAMES PER SECOND
    private final int UPS = 200; // UPDATES PER SECOND
    private ArrayList<IObserver> observers;
    private Player player;
    private ArrayList<Terrain> path;

    private ArrayList<String> highscoreName;
    private ArrayList<IEnemy> enemies;
    private ArrayList<IGameObject> terrains;

    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed, spacePressed;

    private boolean interactableLit = false;
    private boolean stateChangedFlag;
    private GameMap gameMap;
    private File highscoreFile;
    private ArrayList<String> highscoreList;
    private ArrayList<Projectile> projectiles;
    private ArrayList<IGameObject> sprites;
    private Shop shop;

    public Game(){
        player = new Player(32, 32, this);
        this.gameMap = new GameMap(100, 100);
        shop = new Shop();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        terrains = gameMap.getTerrains();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();

        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy(this));
        sprites = new ArrayList<>();
        sprites.add(player);
        sprites.add(shop);
        sprites.addAll(terrains);

        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        enterPressed = false;
        escapePressed = false;
        spacePressed = false;

        stateChangedFlag = false;

        observers = new ArrayList<>();
        highscoreFile = new File("textfiles/highscores.txt");
        highscoreList = getHighScoreList();
    }

    public GameMap getGameMap() {
        return gameMap;
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

    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All Game Map Tiles
     */
    public ArrayList<IGameObject> getTerrainBorder() {
        return null;
//        return terrainBorder.getTerrainBorder();
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

    /**
     * Add an observer. Observers will be notified 120 times per second
     * @param observer observer
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void makePlayerShoot(){
        player.shoot(projectiles);
    }

    /**
     * Updates the current IGameState
     */
    public void update() {


        /* GAME OVER
        if (player.getHealth() < 1){
            game.setState(new MainMenuState());
        }

         */

        for (IGameObject sprite : sprites) {
            sprite.update();
        }

        for(IEnemy enemy : enemies){
            enemy.update();
            //Check if enemy is close enough to damage player, could be done somewhere else also.
            if (CollisionHandler.testCollision(player, (Entity) enemy)) {
                player.damageTaken(1);
            }

        }

        if(playerInRangeOfStore()){
            player.isInteractable =true;
        } player.isInteractable = false;

        for (Projectile p : projectiles) {
            p.update();
        }
    }

    public boolean playerInRangeOfStore() {
        return(CollisionHandler.testCollision(player, shop));
    }

    public void checkIfInteractable(){
        if(playerInRangeOfStore()){
            interactableLit = true;
        }
        interactableLit = false;
    }
    /**
     * Notifies potential observers
     */
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.draw();
        }
    }

    public void resetSpacePressed() {
        this.spacePressed = false;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public ArrayList<Terrain> getPath() {
        return path;
    }

    public Shop getshop() {
        return shop;
    }
}
