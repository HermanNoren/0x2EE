




package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;


class NormalEnemy extends Enemy{


    protected NormalEnemy(int x, int y, Entity targetEntity, Terrain[][] coordinates) {
        super(x, y,targetEntity, coordinates);
        setVelX(0.3);
        setVelY(0.3);
        setMaxHp(100);
        setHealth(100);
    }
    @Override
    public void specialAbility() {

    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }

}
