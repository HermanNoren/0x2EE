
package model.gameobjects.enemies;


import model.Game;

public abstract class EnemyFactory {

    public void spawnEnemies(){

    }
    public abstract IEnemy createEnemy(Game game);
}
