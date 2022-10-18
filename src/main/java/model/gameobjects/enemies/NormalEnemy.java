package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.mapclasses.Tile;

class NormalEnemy extends Enemy{
    protected NormalEnemy(int x, int y, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, coordinates, targetEntity);
        setVelX(0.3);
        setVelY(0.3);
        setMaxHp(20);
        setHealth(20);
    }

    @Override
    public int getDamage() {
        return 1;
    }


    @Override
    public String getType() {
        return "normal";
    }

    @Override
    public int getKillReward() {
        return 100;
    }


}
