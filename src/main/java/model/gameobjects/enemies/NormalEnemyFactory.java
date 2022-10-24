package model.gameobjects.enemies;
import model.gameobjects.Entity;
import model.Vector2;
import model.mapclasses.Tile;

import java.util.List;

/**
 * Factory for creating a NormalEnemy.
 * @author Arthur Alexandersson
 */
public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, int damage, int killReward, Tile spawn, Tile[][] coordinates) {
        return new NormalEnemy((int) spawn.getPos().getX(), (int) spawn.getPos().getY(), damage, killReward, coordinates, targetEntity);
    }
}
