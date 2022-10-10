
package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.Terrain;

import java.util.Random;

public abstract class EnemyFactory {
    public void spawnEnemies(){

    }

    public abstract Enemy createEnemyRandom(Entity targetEntity, Terrain[][] coordinates, Random rand);
    public abstract Enemy createEnemy(Entity targetEntity, Terrain[][] coordinates, int x, int y);
}
