
package model.gameobjects.enemies;


import model.Game;

import java.util.Random;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Enemy createEnemy(Game game, Random rand);
}
