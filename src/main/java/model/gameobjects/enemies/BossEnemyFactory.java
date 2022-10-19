package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class BossEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, List<Tile> passableTiles, Tile[][] coordinates) {
        EnemySpawner enemySpawner = new EnemySpawner(passableTiles);
        Vector2 location = enemySpawner.chooseRandomLocation();
        BossEnemy bossEnemy = new BossEnemy((int) location.getX(),  (int) location.getY(), 5, coordinates, targetEntity);
        return bossEnemy;
    }
}
