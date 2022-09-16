package sprites.enemies;

import controllers.EDirection;
import helperclasses.AStar;
import main.Game;
import mapclasses.Terrain;
import sprites.Entity;

import java.util.Random;

abstract class Enemy extends Entity implements IEnemy {


    protected Enemy(int x, int y, double vel,  int health){
        super(x, y, vel, health);
        Terrain goal = AStar.aStar(new Terrain(getPos()), new Terrain(Game.getInstance().getPlayer().getPos()));
        AStar.printPath(goal);
    }



    @Override
    public void update() {
        super.update();
    }
}
