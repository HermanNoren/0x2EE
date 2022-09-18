package sprites.enemies;

import controllers.EDirection;
import helperclasses.Vector2;
import view.drawers.EnemyDrawer;

import java.util.Vector;

public interface IEnemy{
    Vector2 getPos();
    int getSize();
    EDirection getDirection();
    void specialAbility();
    void update();

}
