package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.mapclasses.Terrain;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy(Entity targetEntity, Terrain[][] coordinates, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, targetEntity ,coordinates);
        return bossEnemy;
    }
}
