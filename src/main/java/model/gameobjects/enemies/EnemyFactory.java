
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;

import java.util.Random;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract Enemy createEnemy(Game game, Random rand);
}
