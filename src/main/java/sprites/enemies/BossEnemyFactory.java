package sprites.enemies;

import sprites.ISprite;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public IEnemy createEnemy() {
        BossEnemy bossEnemy = new BossEnemy(100, 100, 1, 500);

        return bossEnemy;
    }
}
