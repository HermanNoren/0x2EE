package model.gameobjects.enemies;


import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;

import java.util.Random;
import java.util.Vector;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy(Entity targetEntity, GameMap gameMap) {
        EnemySpawner enemySpawner = new EnemySpawner(this, gameMap);
        Vector2 location = enemySpawner.chooseRandomLocation();
        BossEnemy bossEnemy = new BossEnemy((int) location.getX(),  (int) location.getY(), gameMap.getGameMapCoordinates(), targetEntity);
        return bossEnemy;
    }
}
