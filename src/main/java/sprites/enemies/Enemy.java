package sprites.enemies;

public abstract class Enemy extends sprites.Entity {

    int damage = 3;

    private EEnemyType enemyType;

    public Enemy(int position_x, int position_y, int health, EEnemyType enemyType){
        super(position_x, position_y, health);
        enemyType = enemyType;
    }



    public int damageDelt() {
        return 0;
    }


    public void damageTaken(int damage) {

    }
}
