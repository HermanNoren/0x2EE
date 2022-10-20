
package model.gameobjects.enemies;


import model.gameobjects.Entity;

import model.mapclasses.Tile;

/**
 * The Boss extension of Enemy. This extension is stronger and has more health than
 * a regular enemy.
 * @author Arthur Alexandersson, Kasper Ljunggren
 */
public class BossEnemy extends Enemy{

    protected BossEnemy(int x, int y, int damage, int killReward, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, damage, killReward, coordinates, targetEntity);
        setVelX(1);
        setVelY(1);
        setMaxHp(200);
        setHealth(200);
    }

    @Override
    public int getDamage() {
        return super.getDamage();
    }

    @Override
    public String getType() {
        return "boss";
    }

    @Override
    public int getKillReward() {
        return super.getKillReward();
    }

}
