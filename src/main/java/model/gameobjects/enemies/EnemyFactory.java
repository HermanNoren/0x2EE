
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Entity createEnemy(Game game);
}
