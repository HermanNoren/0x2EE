
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Entity createEnemy(Game game);
}
