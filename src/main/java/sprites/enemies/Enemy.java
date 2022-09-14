package sprites.enemies;

import controllers.EDirection;
import sprites.Entity;

import java.util.Random;

public abstract class Enemy extends Entity implements IEnemy {

    protected Enemy(int position_x, int position_y, double vel,  int health){
        super(position_x, position_y, vel, health);
    }

    public void moveEnemy(){

    }
}
