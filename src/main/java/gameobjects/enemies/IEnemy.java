package gameobjects.enemies;

import controllers.EDirection;
import helperclasses.Vector2;

public interface IEnemy{
    Vector2 getPos();

    int getHealth();

    int getSize();
    EDirection getDirection();
    void specialAbility();
    void update();

}
