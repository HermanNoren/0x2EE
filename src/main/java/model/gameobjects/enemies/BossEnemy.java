
package model.gameobjects.enemies;


import model.Game;
import model.helperclasses.Vector2;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y, Game game) {
        super(x, y, game);
        setVelX(1);
        setVelY(1);
        setHealth(500);
    }

    @Override
    public void specialAbility() {

    }
    @Override
    public boolean isPassable() {
        return false;
    }
}
