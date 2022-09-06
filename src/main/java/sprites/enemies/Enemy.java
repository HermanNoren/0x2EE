package sprites.enemies;

import helperclasses.Vector2;
import sprites.Entity;

public class Enemy extends Entity {

    private EnemyType enemyType;

    public Enemy(int position_x, int position_y, int health, EnemyType enemyType){
        super(position_x, position_y, health);
        this.enemyType = enemyType;
    }


}
