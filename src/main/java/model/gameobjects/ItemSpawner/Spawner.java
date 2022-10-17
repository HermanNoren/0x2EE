package model.gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.enemies.Enemy;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Spawner{

    private Game game;
    private List<Double> x_values, y_values;
    private List<Enemy> enemies;
    private double avg_x, avg_y;

    private List<IItem> spawnedItems;
    private Random rand = new Random();

    public Spawner(Game game){
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
        List<Tile> locations = game.getGameMap().getPassableTiles();
        double tile_x, tile_y;
        double difference = 1000000;
        double current_difference = 0;
        Tile closest_tile = new Tile(0,0);
        for (Tile tile : locations) {
            tile_x = tile.getPos().getX();
            tile_y = tile.getPos().getY();
            current_difference = Math.abs(tile_x - x) + Math.abs(tile_y - y);
            if (current_difference <= difference) {
                difference = current_difference;
                closest_tile = tile;
            }
        }
        return closest_tile.getPos();
    }

    /**
     * Returns a location to spawn an item, in regard to current enemy positions
     * @return location to spawn item
     */

    private Vector2 getSpawnLocation() {
        List<Tile> locations = game.getGameMap().getPassableTiles();
        enemies = game.getEnemies(); //G0ra interface till game, med getEnemies, getPlayer osv..
        if (enemies.size() <= 1) {
           return getRandomPassableLocation(locations);
        } else {
            x_values = new ArrayList<>();
            y_values = new ArrayList<>();
            for (Entity enemy : enemies) {
                x_values.add(enemy.getPos().getX());
                y_values.add(enemy.getPos().getY());
            }
            avg_x = getAverage(x_values);
            avg_y = getAverage(y_values);
            return getClosestLocation(avg_x,avg_y);
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
        return Collections.unmodifiableList(spawnedItems);
    }


    /**
     * Removes a specific spawned item
     * @param item item to remove
     */
    public void clearItem(IItem item){
        spawnedItems.remove(item);
    }
}
