package sprites.enemies;

import sprites.ISprite;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public IEnemy createEnemy() {
        NormalEnemy normalEnemy = new NormalEnemy(200, 150, 0.3, 100);
        return normalEnemy;
    }
}
