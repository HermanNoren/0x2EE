package gameobjects.enemies;

import helperclasses.Vector2;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y) {
        super(x, y);
        setVelX(1);
        setVelY(1);
        setHealth(500);
    }

    @Override
    public void specialAbility() {

    }

    @Override
    public Vector2 getCenter() {
        return null;
    }
}
