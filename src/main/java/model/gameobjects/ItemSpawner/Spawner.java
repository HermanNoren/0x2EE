package model.gameobjects.ItemSpawner;

import model.Game;
import model.gameinterfaces.IHasEnemies;
import model.gameobjects.Entity;
import model.gameobjects.enemies.Enemy;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Spawner{

    private List<Tile> passableTiles;

    private IHasEnemies game;
    private List<Double> xValues, yValues;
    private List<Enemy> enemies;
    private double averageX, averageY;

    private List<IItem> spawnedItems;
    private Random rand = new Random();

    public Spawner(List<Tile> passableTiles, IHasEnemies game){
        this.passableTiles = passableTiles;
        this.game = game;
        spawnedItems = new ArrayList<>();
    }

    /**
     * Returns the average of a list of doubles
     * @param list list of doubles
     * @return average
     */

    private double getAverage(List<Double> list){
        double sum = 0;
        for (Double x : list){
            sum+= x;
        }
        return sum / list.size();
    }

    private Vector2 getRandomPassableLocation(List<Tile> locations){
        int nrPossibleSpawnLocations = locations.size();
        Tile randomSpawnableTile = locations.get(rand.nextInt(nrPossibleSpawnLocations-1));
        double posX = randomSpawnableTile.getPos().getX();
        double posY = randomSpawnableTile.getPos().getY();
        return new Vector2(posX, posY);
    }

    private Vector2 getClosestLocation(double x, double y) {
        double tileX, tileY;
        double difference = 1000000;
        double currentDifference = 0;
        Tile closestTile = new Tile(0,0);
        for (Tile tile : passableTiles) {
            tileX = tile.getPos().getX();
            tileY = tile.getPos().getY();
            currentDifference = Math.abs(tileX - x) + Math.abs(tileY - y);
            if (currentDifference <= difference) {
                difference = currentDifference;
                closestTile = tile;
            }
        }
        return closestTile.getPos();
    }

    /**
     * Returns a location to spawn an item, in regard to current enemy positions
     * @return location to spawn item
     */

    private Vector2 getSpawnLocation() {
        enemies = game.getEnemies();
        if (enemies.size() <= 1) {
           return getRandomPassableLocation(passableTiles);
        } else {
            xValues = new ArrayList<>();
            yValues = new ArrayList<>();
            for (Entity enemy : enemies) {
                xValues.add(enemy.getPos().getX());
                yValues.add(enemy.getPos().getY());
            }
            averageX = getAverage(xValues);
            averageY = getAverage(yValues);
            return getClosestLocation(averageX, averageY);
        }
    }

    /**
     * Either spawns a random item or does nothing
     */

    public void spawnItem(){
        int r = rand.nextInt(2);
        switch (r){
            case 0 -> spawnedItems.add(new Coin(getSpawnLocation()));
            case 1 -> spawnedItems.add(new Potion(getSpawnLocation()));
        }

    }

    /**
     * Returns a list of all current spawned items
     * @return list of spawned items
     */

    public List<IItem> getSpawnedItems(){
        return new ArrayList<>(spawnedItems);
    }


    /**
     * Removes a specific spawned item
     * @param item item to remove
     */
    public void clearItem(IItem item){
        spawnedItems.remove(item);
    }
}
