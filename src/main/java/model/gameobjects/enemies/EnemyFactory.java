
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.mapclasses.Terrain;

import java.util.Random;

public abstract class EnemyFactory {
    public void spawnEnemies(){

    }

    public abstract Enemy createEnemy(Entity targetEntity, Terrain[][] coordinates, Random rand);
}
