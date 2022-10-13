package model;

import config.Config;
import controllers.EDirection;
import model.helperclasses.ShopTransaction;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.enemies.*;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.*;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.Shop;
import model.helperclasses.HighscoreHandler;
import model.helperclasses.collision.ECollisionAxis;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import java.io.*;
import java.util.*;
import java.util.Random;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game implements IProjectileAddable{
    private Player player;
    private List<Terrain> path;
    private List<String> highscoreName;
    private List<IGameObject> terrains;
    private List<Enemy> enemies;
    private GameMap gameMap;
    private File highscoreFile;
    private List<String> highscoreList;
    private List<Projectile> projectiles;
    private Shop shop;
    private HighscoreHandler highscoreHandler;
    private Spawner spawner;
    private Random random = new Random();
    private Boolean playerDead;
    private Boolean paused;

    private final int mapSize;
    private ShopTransaction shopTransaction;

    public Game() {
        mapSize = 25;
        newGame();
        highscoreHandler = new HighscoreHandler();
        highscoreList = highscoreHandler.getHighscoreList();
    }

    public void newGame() {
        this.gameMap = new GameMap(mapSize, mapSize);
        this.player = new Player(500, 500, gameMap.getGameMapCoordinates());
        shop = new Shop(200, 100);
        this.shopTransaction = new ShopTransaction(this.getPlayer());
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();
        playerDead = false;
        EnemyFactory enemyFactory = new NormalEnemyFactory();

        enemies.add(enemyFactory.createEnemy(player, gameMap, random));
        enemies.add(enemyFactory.createEnemy(player, gameMap, random));
        enemies.add(enemyFactory.createEnemy(player, gameMap, random));
        enemies.add(enemyFactory.createEnemy(player, gameMap, random));


        spawner = new Spawner(this);
        paused = false;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public int getMapSize() { return mapSize; }

    public List<String> getHighscoreName() {
        return new ArrayList<>(highscoreName);
    }

    public boolean isTopFive() {
        int oldScore;
        for (String playerScore : highscoreList) {
            oldScore = Integer.valueOf(playerScore.split(":")[1]);
            if (player.getScore() >= oldScore) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the list containing highscores.
     */
    public void updateHighscoreList() {
        highscoreHandler.saveHighscore(String.join("", highscoreName), player.getScore());
    }


    public void deleteLetter() {
        if (highscoreName.size() > 0) {
            highscoreName.remove(highscoreName.size() - 1);
        }
    }

    public void updateName(String letter) {
        if (highscoreName.size() < 6) {
            highscoreName.add(letter);
        }
    }

    /**
     * Returns the instance of the player
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return new ArrayList<>(enemies);
    }

    public List<IItem> getItems() {
        return spawner.getSpawnedItems();
    }

    public void makePlayerShoot() {
        player.shoot(this);
    }

    @Override
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    public void pause() {
        player.setDirection(EDirection.NOT_MOVING);
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * Updates the current game state
     * @param dt time passed since last update
     */
    public void update(double dt) {
        if (player.getHealth() < 1) {
            playerDead = true;
        }
        updatePlayer(dt);
        updateEnemies(dt);
        updateItems();

        for (IProjectile projectile : getProjectiles()){
            projectile.update(dt);
        }

        /**
         * See if player is on shop, first for controller second for shop drawer
         */
        player.isOnShop = isPlayerInRangeOfShop();
        shop.playerOnShop = player.isOnShop;
    }

    /**
     * Updates player position and checks for collisions with terrain
     * @param dt time passed since last update
     */
    private void updatePlayer(double dt){
        player.moveX(dt);
        collisionCheck(ECollisionAxis.X_AXIS);
        player.moveY(dt);
        collisionCheck(ECollisionAxis.Y_AXIS);
    }

    /**
     * Updates enemy states and provides necessary logic for death and collisions with projectiles
     * @param dt time passed since last update
     */
    private void updateEnemies(double dt){
        Iterator<Enemy> enemyIter = getEnemies().iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            if (enemy.getHealth() < 1) {
                spawner.spawnItem();
                player.addScore(100);
                enemies.remove(enemy);
                break;
            }
            enemy.update(dt);
            //Check if enemy is close enough to damage player, could be done somewhere else also.
            if (CollisionHandler.testCollision(player, enemy)) {
                this.player.damageTaken(1);
            }
            // Check if projectile hits enemy
            Iterator<Projectile> pIter = getProjectiles().iterator();
            while (pIter.hasNext()) {
                Projectile pr = pIter.next();
                if (CollisionHandler.testCollision(enemy, pr)) {
                    enemy.damageTaken(player.getWeapon().damage);
                    projectiles.remove(pr);
                    break;
                }
            }
        }
    }

    /**
     * Checks if spawned items are picked up/consumed by the player
     */
    private void updateItems(){
        for (IItem item : spawner.getSpawnedItems()) {
            if (CollisionHandler.testCollision(item, player)) {
                item.consume(player);
                spawner.clearItem(item);
                break;
            }
        }
    }

    /**
     * Checks for collisions with terrain and corrects position if collision occurs
     * @param axis tells the method which axis to consider
     */
    private void collisionCheck(ECollisionAxis axis){
        List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player, gameMap.getGameMapCoordinates());
        for (Terrain t : collidedTerrain) {
            Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, axis);
            if (collisionTypes.get("right")) {
                player.setPosX(t.getPos().getX() - player.getWidth());
                player.stopCurrentMovement();
            }
            if (collisionTypes.get("left")) {
                player.setPosX((t.getPos().getX() + t.getWidth()));
                player.stopCurrentMovement();
            }
            if (collisionTypes.get("top")) {
                player.setPosY(t.getPos().getY() + player.getHeight());
                player.stopCurrentMovement();
            }
            if (collisionTypes.get("bottom")) {
                player.setPosY((t.getPos().getY() - t.getHeight()));
                player.stopCurrentMovement();
            }
        }
    }

    public Boolean isPlayerDead() {
        return playerDead;
    }


    public boolean isPlayerInRangeOfShop() {
        return (CollisionHandler.testCollision(player, shop));
    }

    public List<Projectile> getProjectiles() {
        return new ArrayList<>(projectiles);
    }

    public Shop getShop() {
        return shop;
    }
    public ShopTransaction getShopTransaction(){
        return shopTransaction;
    }

}
