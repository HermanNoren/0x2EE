package sprites.enemies;

public class NormalEnemy extends Enemy implements IEnemy {


    public NormalEnemy(int position_x, int position_y, int health, EEnemyType enemyType) {
        super(position_x, position_y, health, enemyType);
    }

    @Override
    public EEnemyType getEnemyType() {
        return null;
    }

    @Override
    public void specialAbility() {

    }


}
