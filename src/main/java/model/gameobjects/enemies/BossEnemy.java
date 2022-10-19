
package model.gameobjects.enemies;


import model.gameobjects.Entity;

import model.mapclasses.Tile;

public class BossEnemy extends Enemy{
    private int damage;
    protected BossEnemy(int x, int y, int damage, Tile[][] coordinates, Entity targetEntity) {
        super(x, y,damage, coordinates, targetEntity);
        this.damage = damage;
        setVelX(1);
        setVelY(1);
        setMaxHp(500);
        setHealth(500);
    }

    @Override
    public int getDamage() {
        return damage;
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
