




package model.gameobjects.enemies;

import model.Game;
import model.helperclasses.Vector2;


class NormalEnemy extends Enemy{


    protected NormalEnemy(int x, int y) {
        super(x, y);
        setVelX(0.3);
        setVelY(0.3);
        setHealth(100);
        setMaxHp(100);
    }
    @Override
    public void specialAbility() {

    }

    @Override
    public void update(double dt) {

    }


    @Override
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
