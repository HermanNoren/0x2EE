package model;

import model.gameobjects.ItemSpawner.IItem;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.Entity;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.Shop;
import model.helperclasses.HighscoreHandler;
import model.helperclasses.collision.ECollisionAxis;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;

import view.IObserver;

import java.io.*;
import java.util.*;
import java.util.Random;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game {
    private List<IObserver> observers;
    private Player player;
    private List<Terrain> path;
    private List<String> highscoreName;
    private List<Entity> enemies;
    private List<IGameObject> terrains;
    private boolean stateChangedFlag;
    private GameMap gameMap;
    private File highscoreFile;
    private List<String> highscoreList;
    private List<Projectile> projectiles;
    private List<IGameObject> gameObjects;
    private Shop shop;
    private HighscoreHandler highscoreHandler;
    private Spawner spawner;
    private Random random = new Random();
    private Boolean playerDead;
    private Projectile pendingBullet;

    public Game() {
        this.gameMap = new GameMap(50, 50);
        this.player = new Player(48, 48, gameMap.getGameMapCoordinates());
        shop = new Shop(200, 100);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();
        playerDead = false;
        EnemyFactory enemyFactory = new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy(gameMap, player, random));
        enemies.add(enemyFactory.createEnemy(gameMap, player, random));
        spawner = new Spawner(this);
        gameObjects = new ArrayList<>();
        gameObjects.add(shop);
        stateChangedFlag = false;
        observers = new ArrayList<>();
        highscoreHandler = new HighscoreHandler();
        highscoreList = highscoreHandler.getHighscoreList();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<String> getHighscoreName() {
        return highscoreName;
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

    public List<Entity> getEnemies() {
        return enemies;
    }

    public List<IItem> getItems() {
        return spawner.getSpawnedItems();
    }

    /**
     * Add an observer. Observers will be notified 120 times per second
     *
     * @param observer observer
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void makePlayerShoot() {
        player.shoot(projectiles);
    }

    /**
     * Updates the current game state
     */
    public void update(double dt) {
        if (!(player.getHealth() < 1)) {

            player.moveX(dt);
            List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player, gameMap.getGameMapCoordinates());
            for (Terrain t : collidedTerrain) {
                Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, ECollisionAxis.X_AXIS);
                if (collisionTypes.get("right")) {
                    player.setPosX(t.getPos().getX() - player.getWidth());
                    player.stopCurrentMovement();
                }
                if (collisionTypes.get("left")) {
                    player.setPosX((t.getPos().getX() + t.getWidth()));
                    player.stopCurrentMovement();
                }
            }

            player.moveY(dt);
            collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player, gameMap.getGameMapCoordinates());
            for (Terrain t : collidedTerrain) {
                Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, ECollisionAxis.Y_AXIS);
                if (collisionTypes.get("top")) {
                    player.setPosY(t.getPos().getY() + player.getHeight());
                    player.stopCurrentMovement();
                }
                if (collisionTypes.get("bottom")) {
                    player.setPosY((t.getPos().getY() - t.getHeight()));
                    player.stopCurrentMovement();
                }
            }

            for (IGameObject gameObject : gameObjects) {
                gameObject.update(dt);
            }

            for (Projectile projectile : getProjectiles()){
                projectile.update(dt);
            }

            Iterator<Entity> enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                Entity enemy = enemyIter.next();
                if (enemy.getHealth() < 1) {
                    spawner.spawnItem();
                    player.addScore(100);
                    enemyIter.remove();
                    break;
                }
                enemy.update(dt);

                //Check if enemy is close enough to damage player, could be done somewhere else also.
                if (CollisionHandler.testCollision(player, enemy)) {
                    player.damageTaken(1);
                }
                // Check if projectile hits enemy
                Iterator<Projectile> pIter = projectiles.iterator();
                while (pIter.hasNext()) {
                    Projectile pr = pIter.next();
                    if (CollisionHandler.testCollision(enemy, pr)) {
                        enemy.damageTaken(10);
                        pIter.remove();
                        break;
                    }
                }
            }

            /**
             * See if the player is close enough to shop.
             */
            shop.playerOnShop = isPlayerInRangeOfShop();

            for (IItem item : spawner.getSpawnedItems()) {
                if (CollisionHandler.testCollision(item, player)) {
                    item.consume(player);
                    spawner.clearItem(item);
                    break;
                }
            }

        } else {
            playerDead = true;
        }
    }

    public Boolean isPlayerDead() {
        return playerDead;
    }


    public boolean isPlayerInRangeOfShop() {
        return (CollisionHandler.testCollision(player, shop));
    }

    /**
     * Notifies potential observers
     */
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.draw();
        }
    }

    public List<Projectile> getProjectiles() {
        return new ArrayList<>(projectiles);
    }

    public Shop getShop() {
        return shop;
    }

}
