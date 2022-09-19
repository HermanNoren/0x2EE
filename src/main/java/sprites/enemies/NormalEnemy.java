package sprites.enemies;

import controllers.EDirection;
import helperclasses.Vector2;

class NormalEnemy extends Enemy{


    protected NormalEnemy(int x, int y, double vel, int health) {
        super(x, y, vel, health);
    }
    @Override
    public void specialAbility() {

    }


    @Override
    public Vector2 getCenter() {
        return null;
    }
}
