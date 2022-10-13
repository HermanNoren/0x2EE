
package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.GameMap;

import java.util.Random;

public abstract class EnemyFactory {
    public void spawnEnemies(){

    }

    public abstract Enemy createEnemy(Entity targetEntity,GameMap gameMap);

}
