
package model.gameobjects.enemies;


import model.gameobjects.Entity;

import model.mapclasses.Tile;

public class BossEnemy extends Enemy{

    protected BossEnemy(int x, int y, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, coordinates, targetEntity);
        setVelX(1);
        setVelY(1);
        setMaxHp(500);
        setHealth(500);
    }

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public String getType() {
        return "boss";
    }

    @Override
    public int getSCoreReward() {
        return 500;
    }

}
