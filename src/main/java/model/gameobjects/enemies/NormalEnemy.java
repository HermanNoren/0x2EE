




package model.gameobjects.enemies;

import model.Game;
import model.helperclasses.Vector2;


class NormalEnemy extends Enemy{


    protected NormalEnemy(int x, int y, Game game) {
        super(x, y, game);
        setVelX(0.3);
        setVelY(0.3);
        setHealth(100);
        setMaxHp(100);
    }
    @Override
    public void specialAbility() {

    }


    @Override
    public Vector2 getCenter() {
        return null;
    }
}
