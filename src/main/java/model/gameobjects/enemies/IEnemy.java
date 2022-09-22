package model.gameobjects.enemies;

import controllers.EDirection;
import model.helperclasses.Vector2;

public interface IEnemy{
    Vector2 getPos();
    int getSize();
    EDirection getDirection();
    void specialAbility();
    void update();

}
