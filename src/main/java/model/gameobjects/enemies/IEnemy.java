package model.gameobjects.enemies;

import model.gameobjects.Entity;

public interface IEnemy{
    String getType();
    int getDamage();
    int getSCoreReward();
    void update(double dt);
    Entity getTargetEntity();
}
