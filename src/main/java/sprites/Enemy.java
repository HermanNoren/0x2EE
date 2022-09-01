package sprites;

import helperclasses.Vector2;

public class Enemy {
    private EnemyType enemyType;
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;


    public Enemy(int x, int y, EnemyType enemyType){
        this.pos = new Vector2(x, y);
        this.vel = new Vector2(1, 1);
        this.acc = new Vector2(0, 0);
        this.enemyType = enemyType;
    }
}
