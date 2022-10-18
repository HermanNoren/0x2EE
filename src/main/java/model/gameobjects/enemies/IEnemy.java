package model.gameobjects.enemies;

import model.gameobjects.Entity;

public interface IEnemy{
    int getKillReward();
    void update(double dt);
    Entity getTargetEntity();
}
