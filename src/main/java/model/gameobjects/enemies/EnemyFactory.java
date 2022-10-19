
package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating enemies.
 */
public abstract class EnemyFactory {
    public abstract Enemy createEnemy(Entity targetEntity, int damage, int killReward, List<Tile> passableTiles, Tile[][] coordinates);
}
