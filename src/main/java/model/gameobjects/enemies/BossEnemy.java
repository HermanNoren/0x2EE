
package model.gameobjects.enemies;


import model.gameobjects.Entity;

import model.mapclasses.Tile;

/**
 * The Boss extension of Enemy. This extension is stronger and has more health than
 * a regular enemy.
 */
public class BossEnemy extends Enemy{
    private int damage;
    protected BossEnemy(int x, int y, int damage, int killReward, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, damage, killReward, coordinates, targetEntity);
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
