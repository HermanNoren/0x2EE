package sprites.enemies;

import helperclasses.Vector2;

public class BossEnemy extends Enemy{

    public BossEnemy(int position_x, int position_y, double vel, int health) {
        super(position_x, position_y, vel, health);
    }

    @Override
    public void specialAbility() {

    }

    @Override
    public Vector2 getCenter() {
        return null;
    }
}