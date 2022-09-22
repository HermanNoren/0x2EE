package gameobjects.enemies;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public IEnemy createEnemy() {
        NormalEnemy normalEnemy = new NormalEnemy(200, 150, 0.3, 1000);
        return normalEnemy;
    }
}
