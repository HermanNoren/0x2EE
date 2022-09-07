package sprites.enemies;

import sprites.Entity;

import java.awt.*;

public class Enemy extends Entity {

    int damage = 3;

    private EnemyType enemyType;

    public Enemy(int position_x, int position_y, int health, EnemyType enemyType){
        super(position_x, position_y, health);
        this.enemyType = enemyType;
    }



    public int damageDelt() {
        return 0;
    }


    public void damageTaken(int damage) {

    }
}
