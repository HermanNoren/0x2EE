package model;

import config.Config;
import model.gameinterfaces.IGame;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.enemies.*;
import model.gameobjects.EDirection;
import model.helperclasses.TransactionHandler;
import model.helperclasses.collision.CollisionHandler;
import model.gameobjects.*;
import model.gameobjects.ItemSpawner.*;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.gameobjects.Shop;
import model.helperclasses.HighscoreHandler;
import model.helperclasses.collision.ECollisionAxis;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.*;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 * @author Arthur Alexandersson, Gustav Gille, Herman Noren, Kasper Ljunggren, Rickard Leksell
 */
public class Game implements IGame {
    private Player player;
    private List<String> highscoreName;

    private List<Enemy> enemies;
    private GameMap gameMap;

    private List<String> highscoreList;
    private List<Projectile> projectiles;
    private Shop shop;
    private HighscoreHandler highscoreHandler;
    private Spawner spawner;

    private boolean playerDead;
    private TransactionHandler transactionHandler;
    private Boolean paused;

    private final int mapSize;
    private EnemyFactory enemyFactory;
    private int spawnCounter = 1;
    private boolean bossSpawnedFlag;

    private List<ISoundObserver> soundObservers;

    public Game() {
        mapSize = 25;
        newGameRound();
        highscoreHandler = new HighscoreHandler();
        highscoreList = highscoreHandler.getHighscoreList();
        soundObservers = new ArrayList<>();
    }

    @Override
    public void newGameRound() {
        this.spawnCounter = 1;
        this.gameMap = new GameMap(Config.MAP_WIDTH, Config.MAP_HEIGHT, true);
        this.player = new Player(500, 500, gameMap.getGameMapCoordinates());
        this.shop = new Shop(100, 100);
        this.transactionHandler = new TransactionHandler(this.getPlayer());
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.highscoreName = new ArrayList<>();
        this.playerDead = false;
        this.spawner = new Spawner(gameMap.getPassableTiles(),this);
        this.paused = false;
        this.bossSpawnedFlag = false;
    }
    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public GameMap getGameMap() {
        return gameMap;
    }
    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getMapSize() { return mapSize; }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public List<String> getHighscoreName() {
        return new ArrayList<>(highscoreName);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isTopFive() {
        int oldScore;
        if (player.getScore() > 0) {
            if (highscoreHandler.getHighscoreList().size() < 5) {
                return true;
            }
            for (String playerScore : highscoreList) {
                oldScore = Integer.valueOf(playerScore.split(":")[1]);
                if (player.getScore() >= oldScore) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the list containing highScores.
     */
    @Override
    public void updateHighscoreList() {
        highscoreHandler.saveHighscore(String.join("", highscoreName), player.getScore());
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public void deleteLetter() {
        if (highscoreName.size() > 0) {
            highscoreName.remove(highscoreName.size() - 1);
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
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
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public List<Enemy> getEnemies() {
        return new ArrayList<>(enemies);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public List<IItem> getItems() {
        return spawner.getSpawnedItems();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void makePlayerShoot() {
        player.shoot(this);
        notifySoundObservers(EGameEvents.PLAYER_SHOOT);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void pause() {
        player.setDirection(EDirection.NOT_MOVING);
        paused = true;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void resume() {
        paused = false;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isPaused() {
        return paused;
    }

    public boolean getBossSpawnedFlag() { return bossSpawnedFlag; }

    public void resetBossSpawnFlag() { bossSpawnedFlag = false; }

    /**
     * Updates the current game state
     */
    @Override
    public void update(double dt) {
        if (player.getHealth() < 1) {
            notifySoundObservers(EGameEvents.PLAYER_DEAD);
            playerDead = true;
        }
        updatePlayer(dt);
        updateEnemies(dt);
        updateItems();
        updateProjectile(dt);
        checkIfProjectileHitsTerrain();
        isPlayerOnShop();
    }

    private void updateProjectile(double dt) {
        for (Projectile projectile : getProjectiles()){
            projectile.update(dt);

        }
    }

    private void checkIfProjectileHitsTerrain(){
        Iterator<Projectile> pIter = getProjectiles().iterator();
        while (pIter.hasNext()){
            Projectile p = pIter.next();
            List<Tile> collidedTiles = CollisionHandler.getSpecificTileCollisions(p, gameMap.getGameMapCoordinates());
            if (collidedTiles.size() > 0){
                projectiles.remove(p);
                break;
            }
        }
    }


    /**
     * Call method to see if the player is on the shop.
     */
    private void isPlayerOnShop() {
        shop.playerOnShop = CollisionHandler.testCollision(player, shop);
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
     * @param dt time passed since last update.
     */
    private void updateEnemies(double dt){
        Iterator<Enemy> enemyIter = getEnemies().iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            if (enemy.getHealth() <= 0) {
                spawner.spawnItem();
                player.addScore(enemy.getKillReward());
                enemies.remove(enemy);
                break;
            }
            enemy.update(dt);
            //Check if enemy is close enough to damage player, could be done somewhere else also.
            if (CollisionHandler.testCollision(player, enemy)) {
                this.player.damageTaken(enemy.getDamage());
            }
            checkIfProjectileHitsEnemy(enemy);
        }
    }

    private void checkIfProjectileHitsEnemy(Enemy enemy) {
        Iterator<Projectile> pIter = getProjectiles().iterator();
        while (pIter.hasNext()) {
            Projectile pr = pIter.next();
            if (CollisionHandler.testCollision(enemy, pr)) {
                enemy.damageTaken(player.getWeapon().currentStats());
                projectiles.remove(pr);
                break;
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
        List<Tile> collidedTile = CollisionHandler.getSpecificTileCollisions(player, gameMap.getGameMapCoordinates());
        for (Tile t : collidedTile) {
            Map<String, Boolean> collisionTypes = CollisionHandler.getCollisionDirection(player, t, axis);
            if (collisionTypes.get("right")) {
                player.setPosX(t.getPos().getX() - player.getWidth());
                player.stopCurrentXMovement();
            }
            if (collisionTypes.get("left")) {
                player.setPosX((t.getPos().getX() + t.getWidth()));
                player.stopCurrentXMovement();
            }
            if (collisionTypes.get("top")) {
                player.setPosY(t.getPos().getY() + player.getHeight());
                player.stopCurrentYMovement();
            }
            if (collisionTypes.get("bottom")) {
                player.setPosY((t.getPos().getY() - t.getHeight()));
                player.stopCurrentYMovement();
            }
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isPlayerDead() {
        return playerDead;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public List<Projectile> getProjectiles() {
        return new ArrayList<>(projectiles);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Shop getShop() {
        return shop;
    }
    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public TransactionHandler getShopTransaction(){
        return transactionHandler;
    }
    /**
     *
     */
    @Override
    public void spawnEnemy(){
        int damage;
        int killReward;
        if((spawnCounter % 2) == 0 && spawnCounter != 0){
            killReward = 500;
            damage = 5;
            enemyFactory = new BossEnemyFactory();
            bossSpawnedFlag = true;
            notifySoundObservers(EGameEvents.BOSS_SPAWN);
        }else{
            damage = 1;
            killReward = 100;
            enemyFactory = new NormalEnemyFactory();
        }
        spawnCounter++;
        enemies.add(enemyFactory.createEnemy(player, damage, killReward, gameMap.getPassableTiles(), gameMap.getGameMapCoordinates()));
    }

    @Override
    public void subscribe(ISoundObserver observer) {
        soundObservers.add(observer);
    }

    private void notifySoundObservers(EGameEvents event) {
        for (ISoundObserver observer : soundObservers) {
            observer.notifySoundEvent(event);
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean playerOnShop() {
        return shop.playerOnShop;
    }
}
