package gameobjects.enemies;

import helperclasses.Vector2;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y, double vel, int health) {
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
