
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;

import java.util.Random;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Entity createEnemy(Game game, Random rand);
}
