package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Entity createEnemy(Game game) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, game);

        return bossEnemy;
    }
}
