
package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.List;
import java.util.Random;

public abstract class EnemyFactory {
    public abstract Enemy createEnemy(Entity targetEntity, List<Tile> passableTiles, Tile[][] coordinates);
}
