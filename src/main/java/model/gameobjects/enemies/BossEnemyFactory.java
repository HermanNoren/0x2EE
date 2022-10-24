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
    public Enemy createEnemy(Entity targetEntity, int damage, int killReward, Tile spawn, Tile[][] coordinates) {
        return new BossEnemy((int) spawn.getPos().getX(),  (int) spawn.getPos().getY(), damage, killReward, coordinates, targetEntity);
    }
}
