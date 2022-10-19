package model.gameobjects.enemies;
import model.gameobjects.Entity;
import model.Vector2;
import model.mapclasses.Tile;

import java.util.List;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, int damage, int killReward, List<Tile> passableTiles, Tile[][] coordinates) {
        EnemySpawner enemySpawner = new EnemySpawner(passableTiles);
        Vector2 location = enemySpawner.chooseRandomLocation();
        NormalEnemy normalEnemy = new NormalEnemy((int) location.getX(), (int) location.getY(), damage, killReward, coordinates, targetEntity);
        return normalEnemy;
    }
}
