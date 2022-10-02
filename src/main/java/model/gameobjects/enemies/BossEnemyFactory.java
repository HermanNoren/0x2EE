package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Entity createEnemy(Game game, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, game);
        return bossEnemy;
    }
}
