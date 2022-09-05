package sprites.enemies;

import helperclasses.Vector2;
import sprites.Entity;

public class Enemy extends Entity {

    private EnemyType enemyType;

    public Enemy(int x, int y, int health, EnemyType enemyType){
        super(x, y, health);
        this.enemyType = enemyType;
    }
}
