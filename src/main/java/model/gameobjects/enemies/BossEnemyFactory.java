package model.gameobjects.enemies;


import model.Game;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy(Game game, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, game.getGameMap().getGameMapCoordinates(), game.getPlayer());
        return bossEnemy;
    }
}
