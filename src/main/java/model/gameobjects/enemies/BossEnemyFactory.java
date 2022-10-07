package model.gameobjects.enemies;


import model.Game;
import model.mapclasses.Terrain;

import java.util.Random;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy(Terrain[][] coordinates, Random random) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, coordinates);
        return bossEnemy;
    }
}
