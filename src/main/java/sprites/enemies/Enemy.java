package sprites.enemies;

import sprites.Entity;

import java.awt.*;

public class Enemy extends Entity {

    private EnemyType enemyType;

    public Enemy(int position_x, int position_y, int health, EnemyType enemyType){
        super(position_x, position_y, health);
        this.enemyType = enemyType;
    }

    @Override
    public void attack() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }
}
