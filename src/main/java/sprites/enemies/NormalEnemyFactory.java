package sprites.enemies;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public IEnemy createEnemy() {
        NormalEnemy normalEnemy = new NormalEnemy(10, 10, 0.5, 100);
        return normalEnemy;
    }
}
