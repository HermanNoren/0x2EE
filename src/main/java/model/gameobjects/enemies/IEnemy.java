package model.gameobjects.enemies;

import model.gameobjects.Entity;

public interface IEnemy{

    int getDamage();
    int getSCoreReward();
    void update(double dt);
    Entity getTargetEntity();
}
