package model.gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.IEnemy;
import model.helperclasses.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Spawner {

    private Game game;

    private List<Double> x_values, y_values;
    private List<Entity> enemies;
    private double avg_x, avg_y;

    private List<IItem> spawnedItems;

    public Spawner(Game game){
        this.game = game;
        spawnedItems = new ArrayList<>();
    }

    private double getAverage(List<Double> list){
        double sum = 0;
        for (Double x : list){
            sum+= x;
        }

        return sum / list.size();
    }

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

    public void spawnItem(){
        Random rand = new Random();
        int r = rand.nextInt(2);
        switch (r) {
            case 0 -> spawnedItems.add(new Coin(getSpawnLocation()));
            case 1 -> spawnedItems.add(new Potion(getSpawnLocation()));
        }

    }

    public List<IItem> getSpawnedItems(){
        return spawnedItems;
    }

    public void clearItem(IGameObject item){
        spawnedItems.remove(item);
    }
}
