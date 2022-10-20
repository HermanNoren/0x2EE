package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.Vector2;
import model.mapclasses.Tile;

import java.util.List;

/**
 * Creates and returns an BossEnemy.
 * @author Arthur Alexandersson
 */
public class BossEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, int damage, int killReward, List<Tile> passableTiles, Tile[][] coordinates) {
        EnemySpawner enemySpawner = new EnemySpawner(passableTiles);
        Vector2 location = enemySpawner.chooseRandomLocation();
        return new BossEnemy((int) location.getX(),  (int) location.getY(), damage, killReward, coordinates, targetEntity);
    }
}
