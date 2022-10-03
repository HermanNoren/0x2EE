package model;

import model.gameobjects.ItemSpawner.IItem;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.enemies.*;
import model.gameobjects.Entity;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.Shop;
import model.helperclasses.HighscoreHandler;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import view.IObserver;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game{
    private List<IObserver> observers;
    private Player player;
    private List<Terrain> path;

    private List<String> highscoreName;
    private List<Entity> enemies;
    private List<IGameObject> terrains;
    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed, spacePressed;
    private boolean stateChangedFlag;
    private GameMap gameMap;
    private File highscoreFile;
    private List<String> highscoreList;
    private List<Projectile> projectiles;
    private List<IGameObject> sprites;
    private Shop shop;
    private HighscoreHandler highscoreHandler;

    private Spawner spawner;

    private Boolean playerDead;

    public Game(){
        player = new Player(32, 32, this);
        this.gameMap = new GameMap(200, 200);
        shop = new Shop();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();
        playerDead = false;
        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        spawner = new Spawner(this);

        sprites = new ArrayList<>();
        sprites.add(player);
        sprites.add(shop);
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        enterPressed = false;
        escapePressed = false;
        spacePressed = false;
        stateChangedFlag = false;
        observers = new ArrayList<>();
        highscoreHandler = new HighscoreHandler();
        highscoreList = highscoreHandler.getHighscoreList();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<String> getHighscoreName(){
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
     * Updates the list containing highscores.
     */
    public void updateHighscoreList(){
        highscoreHandler.saveHighscore(String.join("", highscoreName), player.getScore());
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
    public List<Entity> getEnemies(){
        return enemies;
    }

    public List<IItem> getItems(){
        return spawner.getSpawnedItems();
    }

    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All Game Map Tiles
     */
    public List<IGameObject> getTerrainBorder() {
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
        if (!(player.getHealth() < 1)) {

            for (IGameObject sprite : sprites) {
                sprite.update();
            }

            for (Projectile p : projectiles) {
                p.update();
            }

            Iterator<Entity> enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
               Entity enemy = enemyIter.next();
               enemy.update();
                //Check if enemy is close enough to damage player, could be done somewhere else also.
                if (CollisionHandler.testCollision(player, enemy)) {
                    player.damageTaken(1);
                }
                // Check if projectile hits enemy
                Iterator<Projectile> pIter = projectiles.iterator();
                while (pIter.hasNext()) {
                    Projectile p = pIter.next();
                    if (CollisionHandler.testCollision(enemy, p)) {
                        enemy.damageTaken(10);
                        pIter.remove();
                        if (enemy.getHealth() < 1) {
                            spawner.spawnItem();
                            enemyIter.remove();
                            player.addScore(100);
                        }
                        break;
                    }

                }

            }

            if (playerInRangeOfStore()) {
                player.isInteractable = true;
            } else {
                player.isInteractable = false;
            }

            for (IItem item : spawner.getSpawnedItems()) {
                if (CollisionHandler.testCollision(item, player)) {
                    item.consume(player);
                    spawner.clearItem(item);
                    break; // error om man inte breakar, se ovan
                }
            }

        } else {
            playerDead = true;
        }
    }

    public Boolean isPlayerDead(){
        return playerDead;
    }


    public boolean playerInRangeOfStore() {
        return(CollisionHandler.testCollision(player, shop));
    }

    public void checkIfInteractable(){
        if(playerInRangeOfStore()){
            player.isInteractable = true;
        }
        player.isInteractable = false;
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

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


    public Shop getshop() {
        return shop;
    }

}
