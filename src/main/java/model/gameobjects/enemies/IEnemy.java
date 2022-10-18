package model.gameobjects.enemies;

import model.gameobjects.Entity;

public interface IEnemy{

    int getDamage();
    int getKillReward();
    void update(double dt);
    Entity getTargetEntity();
}
