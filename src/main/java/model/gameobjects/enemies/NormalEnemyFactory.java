package model.gameobjects.enemies;

import model.Game;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public IEnemy createEnemy(Game game) {
        NormalEnemy normalEnemy = new NormalEnemy(200, 150, game);
        return normalEnemy;
    }
}
