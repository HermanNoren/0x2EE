package sprites.enemies;

import controllers.EDirection;

class NormalEnemy extends Enemy{

    protected NormalEnemy(int position_x, int position_y, double vel, int health) {
        super(position_x, position_y, vel, health);
    }

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
