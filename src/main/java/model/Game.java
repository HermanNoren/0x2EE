package model;

import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.enemies.*;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.Entity;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.theShop.Shop;
import model.helperclasses.HighscoreHandler;
import model.helperclasses.collision.ECollisionDirection;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import view.IObserver;

import java.io.*;
import java.util.*;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game{
    private ArrayList<IObserver> observers;
    private Player player;
    private ArrayList<Terrain> path;

    private ArrayList<String> highscoreName;
    private ArrayList<IEnemy> enemies;
    private ArrayList<Terrain> terrains;
    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed, spacePressed;
    private boolean stateChangedFlag;
    private GameMap gameMap;
    private ArrayList<String> highscoreList;
    private ArrayList<Projectile> projectiles;
    private ArrayList<IGameObject> sprites;
    private Shop shop;
    private HighscoreHandler highscoreHandler;

    private Spawner spawner;

    public Game(){
        player = new Player(64, 64, this);
        this.gameMap = new GameMap(100, 100);
        shop = new Shop();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        terrains = gameMap.getTerrains();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();
        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        enemies.add(enemyFactory.createEnemy(this));
        spawner = new Spawner(this);
        sprites = new ArrayList<>();
        //sprites.add(player);
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
        highscoreHandler = new HighscoreHandler();
        highscoreList = highscoreHandler.getHighscoreList();
    }

    public GameMap getGameMap() {
        return gameMap;
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
    public ArrayList<IEnemy> getEnemies(){
        return enemies;
    }

    public ArrayList<IGameObject> getItems(){
        return spawner.getSpawnedItems();
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
     * Updates the current game state
     */
    public void update(double dt) {


        /* GAME OVER
        if (player.getHealth() < 1){
            game.setState(new MainMenuState());
        }

         */

        player.moveX(dt);
        List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player, gameMap.getGameMapCoordinates());
        for (Terrain t : collidedTerrain) {
            Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, ECollisionDirection.X_AXIS);
            if (collisionTypes.get("right")) {
                player.setPosX(t.getPos().x - player.getWidth());
                player.stopCurrentMovement();
            }
            if (collisionTypes.get("left")) {
                player.setPosX((t.getPos().x + t.getWidth()));
                player.stopCurrentMovement();
            }
        }

        player.moveY(dt);
        collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player, gameMap.getGameMapCoordinates());
        for (Terrain t : collidedTerrain) {
            Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, ECollisionDirection.Y_AXIS);
            if (collisionTypes.get("top")) {
                player.setPosY(t.getPos().y + player.getHeight());
                player.stopCurrentMovement();
            }
            if (collisionTypes.get("bottom")) {
                player.setPosY((t.getPos().y - t.getHeight()));
                player.stopCurrentMovement();
            }
        }



        for (IGameObject sprite : sprites) {
            sprite.update(dt);
        }

        for(IEnemy enemy : enemies){
            enemy.update(dt);
            //Check if enemy is close enough to damage player, could be done somewhere else also.
            if (CollisionHandler.testCollision(player, (Entity) enemy)) {
                player.damageTaken(1);
            }

            // Check if projectile hits enemy
            for (Projectile p : projectiles){
                if (CollisionHandler.testCollision((Entity) enemy, p)) {
                    ((Entity) enemy).damageTaken(10);
                    spawner.spawnItem();
                    projectiles.remove(p);
                    break;
                    }
                    // error om man inte breakar för tar bort projectilen

            }

        }

        if(playerInRangeOfStore()){
            player.isInteractable = true;
        }else{
            player.isInteractable = false;
        }

        for (Projectile p : projectiles) {
            p.update(dt);
        }
        for (IGameObject potion : spawner.getSpawnedItems()){
            if (CollisionHandler.testCollision(potion, player)){
                player.setHealth(getPlayer().getHealth() + 100);
                spawner.clearPotion(potion);
                break; // error om man inte breakar, se ovan
            }

        }
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
