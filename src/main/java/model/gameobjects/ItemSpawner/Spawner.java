package model.gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.enemies.Enemy;
import model.helperclasses.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner{

    private Game game;
    private List<Double> x_values, y_values;
    private List<Enemy> enemies;
    private double avg_x, avg_y;

    private List<IItem> spawnedItems;

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

    private Vector2 getSpawnLocation(){
        enemies = game.getEnemies(); //Göra interface till game, med getEnemies, getPlayer osv..
        x_values = new ArrayList<>();
        y_values = new ArrayList<>();
        for (Entity enemy : enemies){
            x_values.add(enemy.getPos().getX());
            y_values.add(enemy.getPos().getY());
        }
        avg_x = getAverage(x_values);
        avg_y = getAverage(y_values);
        return new Vector2(avg_x, avg_y);
    }

    /**
     * Either spawns a random item or does nothing
     */

    public void spawnItem(){
        Random rand = new Random();
        int r = rand.nextInt(2);

        switch (0){
            case 0 -> spawnedItems.add(new Coin(getSpawnLocation()));
            case 1 -> spawnedItems.add(new Potion(getSpawnLocation()));
        }

    }

    /**
     * Returns a list of all current items available on the map
     * @return list of spawned items
     */

    public List<IItem> getSpawnedItems(){
        return spawnedItems;
    }


    /**
     * Removes a specific item from map
     * @param item item to remove
     */
    public void clearItem(IItem item){
        spawnedItems.remove(item);
    }
}
