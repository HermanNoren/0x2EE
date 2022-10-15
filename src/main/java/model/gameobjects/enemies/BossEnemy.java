
package model.gameobjects.enemies;


import model.gameobjects.Entity;

import model.mapclasses.Tile;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, coordinates, targetEntity);
        setVelX(1);
        setVelY(1);
        setMaxHp(500);
        setHealth(500);

    }

    @Override
    public void specialAbility() {

    }

    @Override
    public int getKillReward() {
        return 500;
    }

}
