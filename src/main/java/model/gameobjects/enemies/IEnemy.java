package model.gameobjects.enemies;
import controllers.EDirection;

import controllers.EDirection;
import model.helperclasses.Vector2;


public interface IEnemy{
    Vector2 getPos();

    int getHealth();

    int getSize();
    EDirection getDirection();
    void specialAbility();
    void update(double dt);

}
