package model.gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.enemies.Enemy;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;

import java.util.ArrayList;
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

    /**
     * Returns a location to spawn an item, in regard to current enemy positions
     * @return location to spawn item
     */

    private Vector2 getSpawnLocation() {
        enemies = game.getEnemies(); //Göra interface till game, med getEnemies, getPlayer osv..
        List<Tile> locations = game.getGameMap().getPassableTiles();
        if (enemies.size() <= 1) {
            int nrPossibleSpawnLocations = locations.size();
            Tile randomSpawnableTile = locations.get(rand.nextInt(nrPossibleSpawnLocations-1));
            double posX = randomSpawnableTile.getPos().getX();
            double posY = randomSpawnableTile.getPos().getY();
            return new Vector2(posX, posY);

        } else {
            x_values = new ArrayList<>();
            y_values = new ArrayList<>();
            for (Entity enemy : enemies) {
                x_values.add(enemy.getPos().getX());
                y_values.add(enemy.getPos().getY());
            }
            avg_x = getAverage(x_values);
            avg_y = getAverage(y_values);
            return new Vector2(avg_x, avg_y);
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
