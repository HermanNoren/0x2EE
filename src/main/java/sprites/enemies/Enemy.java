package sprites.enemies;

import controllers.EDirection;
import sprites.Entity;

import java.util.Random;

abstract class Enemy extends Entity implements IEnemy {

    protected Enemy(int x, int y, double vel,  int health){
        super(x, y, vel, health);
    }


    public void moveEnemy(){

    }

    @Override
    public void update() {
        super.update();
    }
}
