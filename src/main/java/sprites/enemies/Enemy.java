package sprites.enemies;

import sprites.Entity;

public class Enemy extends Entity {

    int damage = 3;

    private IEnemyType enemyType;

    public Enemy(int position_x, int position_y, int health, IEnemyType enemyType){
        super(position_x, position_y, health);
        this.enemyType = enemyType;
    }



    public int damageDelt() {
        return 0;
    }


    public void damageTaken(int damage) {

    }
}
