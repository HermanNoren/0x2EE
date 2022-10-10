package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.mapclasses.GameMap;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy( Entity targetEntity, GameMap gameMap, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, gameMap.getGameMapCoordinates(), targetEntity);
        return bossEnemy;
    }
}
