
package model.gameobjects.enemies;


import model.Game;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y, Terrain[][] coordinates) {
        super(x, y, coordinates);
        setVelX(1);
        setVelY(1);
        setHealth(500);
    }

    @Override
    public void specialAbility() {

    }
}
