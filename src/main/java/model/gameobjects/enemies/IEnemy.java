package model.gameobjects.enemies;
import controllers.EDirection;

import model.helperclasses.Vector2;


public interface IEnemy{
    int getHealth();
    int getSize();
    void specialAbility();
    void update(double dt);
}
