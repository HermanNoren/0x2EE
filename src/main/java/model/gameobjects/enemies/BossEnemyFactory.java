package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.GameMap;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy(GameMap gameMap, Entity targetEntity, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, gameMap.getGameMapCoordinates(), targetEntity);
        return bossEnemy;
    }
}
