package model;

import controllers.EDirection;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.Entity;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.theShop.Shop;
import model.helperclasses.HighscoreHandler;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import view.IObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private Random random = new Random();

    public Game(){
        this.gameMap = new GameMap(50, 50);
        this.player = Player.createPlayer(this, random);
        shop = new Shop();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        highscoreName = new ArrayList<>();

        EnemyFactory enemyFactory= new NormalEnemyFactory();

        enemies.add(enemyFactory.createEnemy(this, random));
        enemies.add(enemyFactory.createEnemy(this, random));

        spawner = new Spawner(this);

        sprites = new ArrayList<>();

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

    public List<Entity> getEnemies(){
        return enemies;
    }

    public List<IGameObject> getItems(){
        return spawner.getSpawnedItems();
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

    double speed = 0;
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

        player.moveY(0.5);
        player.moveX(0.5);
        for(IGameObject terrain: gameMap.getTerrains()){
            Map<String, Boolean> collisionTypeX = CollisionHandler.testCollisionWithDirection(player, terrain, "X");
            if(collisionTypeX.get("right")){
                player.setVelX(0);
                player.setAccX(0);
                player.setPosX(terrain.getPos().x - player.getWidth());
            }

            if(collisionTypeX.get("left")){
                player.setVelX(0);
                player.setAccX(0);
                player.setPosX(terrain.getPos().x + player.getWidth());
            }



            Map<String, Boolean> collisionTypeY = CollisionHandler.testCollisionWithDirection(player, terrain, "Y");
            if(collisionTypeY.get("top")){
                player.setVelY(0);
                player.setAccY(0);
                player.setPosY(terrain.getPos().y + player.getHeight());
            }
            if(collisionTypeY.get("bottom")){
                player.setVelY(0);
                player.setAccY(0);
                player.setPosY(terrain.getPos().y - terrain.getHeight());
            }
        }



        for(Entity enemy : enemies){
            enemy.update();
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
            p.update();
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

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


    public Shop getshop() {
        return shop;
    }

    public List<Terrain> getPath() {
        if(path != null){
            return path;
        }
        return null;
    }
    public void setPath(List<Terrain> n){
        if(n != null) {
            this.path = n;
        }
    }
}
