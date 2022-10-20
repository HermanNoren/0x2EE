package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.mapclasses.Tile;

/**
 * The mundane enemy, standard enemy.
 * @author Arthur Alexandersson, Gustav Gille, Herman Norén, Kasper Lundgren, Rickard Leksell
 */
class NormalEnemy extends Enemy{
    protected NormalEnemy(int x, int y, int damage, int killReward, Tile[][] coordinates, Entity targetEntity) {
        super(x, y, damage, killReward, coordinates, targetEntity);

        setVelX(0.3);
        setVelY(0.3);
        setMaxHp(20);
        setHealth(20);
    }

    @Override
    public int getDamage() {
        return super.getDamage();
    }
    @Override
    public String getType() {
        return "normal";
    }

    @Override
    public int getKillReward() {
        return super.getKillReward();
    }


}
