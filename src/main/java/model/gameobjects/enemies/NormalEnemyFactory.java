package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Entity createEnemy(Game game) {
        NormalEnemy normalEnemy = new NormalEnemy(200, 150, game);
        return normalEnemy;
    }
}
