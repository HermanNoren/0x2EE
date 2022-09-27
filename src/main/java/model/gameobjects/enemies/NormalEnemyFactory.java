package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;

import java.util.Random;
import model.gameobjects.Entity;

import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override

    public Entity createEnemy(Game game) {
        Random rand = new Random();
        int x = rand.nextInt(400);
        int y = rand.nextInt(400);
        NormalEnemy normalEnemy = new NormalEnemy(x, y);
        return normalEnemy;
    }
}
