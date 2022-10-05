
package Model.gameobjects.enemies;


import Model.Game;

import java.util.Random;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Enemy createEnemy(Game game, Random rand);
}
