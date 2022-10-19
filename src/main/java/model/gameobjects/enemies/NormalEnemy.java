package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.mapclasses.Tile;

class NormalEnemy extends Enemy{
    private int damage;
    protected NormalEnemy(int x, int y, int damage, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, damage, coordinates, targetEntity);
        this.damage = damage;
        setVelX(0.3);
        setVelY(0.3);
        setMaxHp(20);
        setHealth(20);
    }

    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public String getType() {
        return "normal";
    }

    @Override
    public int getSCoreReward() {
        return 100;
    }


}
