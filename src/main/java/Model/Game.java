package Model;

import Model.gameobjects.ItemSpawner.IItem;
import Model.helperclasses.collision.CollisionHandler;
import Model.gameobjects.*;
import Model.gameobjects.ItemSpawner.Spawner;
import Model.gameobjects.Entity;
import Model.gameobjects.enemies.EnemyFactory;
import Model.gameobjects.enemies.NormalEnemyFactory;
import Model.gameobjects.Shop;
import Model.helperclasses.HighscoreHandler;
import Model.helperclasses.collision.ECollisionAxis;
import Model.mapclasses.GameMap;
import Model.mapclasses.Terrain;
import Model.gameobjects.IGameObject;

import View.IObserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map;

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
    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed, spacePressed;
    private boolean stateChangedFlag;
    private GameMap gameMap;
    private List<String> highscoreList;
    private List<Projectile> projectiles;
    private List<IGameObject> sprites;
    private Shop shop;
    private HighscoreHandler highscoreHandler;

    private Spawner spawner;
    private Random random = new Random();

    private Boolean playerDead;

    public Game() {
        this.gameMap = new GameMap(10, 10);
        this.player = Player.createPlayer(this, random);
        shop = new Shop(200, 100);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        highscoreName = new ArrayList<>();
        this.path = new ArrayList<>();
        playerDead = false;
        EnemyFactory enemyFactory = new NormalEnemyFactory();
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
     *
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
     *
     * @param observer observer
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void makePlayerShoot() {
        player.shoot(projectiles);
    }

    double speed = 0;

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
            for (IGameObject sprite : sprites) {
                sprite.update(dt);
            }

            for (Projectile p : projectiles) {
                p.update(dt);
            }

            Iterator<Entity> enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                Entity enemy = enemyIter.next();
                if (enemy.getHealth() < 1){
                    spawner.spawnItem();
                    player.addScore(100);
                    enemyIter.remove();
                    break;
                }
                enemy.update(dt);

                //Check if enemy is close enough to damage player, could be done somewhere else also.
                if (CollisionHandler.testCollision(player, enemy)) {
                    //player.damageTaken(1);
                }
                // Check if projectile hits enemy
                Iterator<Projectile> pIter =  projectiles.iterator();
                while (pIter.hasNext()) {
                    if (CollisionHandler.testCollision(enemy, pIter.next())) {
                        enemy.damageTaken(10);
                        pIter.remove();
                        break;
                    }
                    // error om man inte breakar för tar bort projectilen
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
                    break; // error om man inte breakar, se ovan
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

    public void resetSpacePressed() {
        this.spacePressed = false;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


    public Shop getShop() {
        return shop;
    }

    public List<Terrain> getPath() {
        if (path != null) {
            return path;
        }
        return null;
    }

    public void setPath(List<Terrain> n) {
        if (n != null) {
            this.path = n;
        }
    }
}
