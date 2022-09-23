package model.gameobjects.enemies;


import model.Game;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public IEnemy createEnemy(Game game) {
        BossEnemy bossEnemy = new BossEnemy(100, 100, game);

        return bossEnemy;
    }
}
