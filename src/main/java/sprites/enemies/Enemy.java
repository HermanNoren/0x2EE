package sprites.enemies;

import controllers.EDirection;
import helperclasses.AStar;
import helperclasses.Vector2;
import main.Game;
import mapclasses.Terrain;
import sprites.Entity;

import java.util.Random;

abstract class Enemy extends Entity implements IEnemy {


    protected Enemy(int x, int y, double vel,  int health){
        super(x, y, vel, health);

    }



    @Override
    public void update() {
        Game game = Game.getInstance();
        Vector2 vec = new Vector2 (Math.round(game.getPlayer().getPos().getX()), Math.round(game.getPlayer().getPos().getY()));
        String keyPlayer = "" + (int) vec.getX() + (int) vec.getY();
        String keyEnemy = "" + (int) this.getPos().getX() + (int) this.getPos().getY();
        Terrain goal = null;
        if (game.getGrass().get(keyEnemy) != null) 
            goal = AStar.aStar(game.getGrass().get(keyEnemy), game.getGrass().get(keyPlayer));
        if (goal != null) System.out.println(goal.getPos().getX());
        //else System.out.println("Hej");
        super.update();
    }
}
