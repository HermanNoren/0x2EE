package gameobjects.enemies;

public class BossEnemyFactory extends EnemyFactory{

    @Override
    public IEnemy createEnemy() {
        BossEnemy bossEnemy = new BossEnemy(100, 100);

        return bossEnemy;
    }
}
