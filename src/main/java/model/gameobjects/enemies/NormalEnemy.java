package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.mapclasses.Terrain;

class NormalEnemy extends Enemy{
    protected NormalEnemy(int x, int y, Terrain[][] coordinates, Entity targetEntity) {
        super(x, y, coordinates, targetEntity);
        setVelX(0.3);
        setVelY(0.3);
        setMaxHp(11);
        setHealth(20);
    }

    @Override
    public void specialAbility() {

    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }

}
