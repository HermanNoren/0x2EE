
package model.gameobjects.enemies;


import model.Game;
import model.mapclasses.Terrain;

import java.util.Random;

public abstract class EnemyFactory {
    public void spawnEnemies(){

    }

    public abstract Enemy createEnemy(Terrain[][] coordinates, Random rand);
}
