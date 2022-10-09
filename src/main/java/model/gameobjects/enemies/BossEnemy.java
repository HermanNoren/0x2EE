
package model.gameobjects.enemies;


import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;

public class BossEnemy extends Enemy{

    public BossEnemy(int x, int y, Entity targetEntity, Terrain[][] coordinates) {
        super(x, y, targetEntity, coordinates);
        setVelX(1);
        setVelY(1);
        setHealth(500);
    }

    @Override
    public void specialAbility() {

    }
}
