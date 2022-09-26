package model.gameobjects.enemies;

import model.Game;

import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public IEnemy createEnemy(Game game) {
        Random rand = new Random();
        int x = rand.nextInt(400);
        int y = rand.nextInt(400);
        NormalEnemy normalEnemy = new NormalEnemy(x, y, game);
        return normalEnemy;
    }
}
