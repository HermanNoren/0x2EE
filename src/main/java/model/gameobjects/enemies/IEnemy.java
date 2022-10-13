package model.gameobjects.enemies;

import model.gameobjects.Entity;

public interface IEnemy{
    void specialAbility();
    void update(double dt);
    Entity getTargetEntity();
}
