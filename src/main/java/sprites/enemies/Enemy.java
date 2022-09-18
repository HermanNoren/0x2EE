package sprites.enemies;

import config.Config;
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
        int size = Config.SPRITE_SIZE;
        String keyPlayer = "" + (int) (size*(Math.round(game.getPlayer().getPos().getX()/size))) + (int) (size*(Math.round(game.getPlayer().getPos().getY()/size)));
        String keyEnemy = "" + (int) (size*(Math.round(this.getPos().getX()/size))) + (int) (size*(Math.round(this.getPos().getY()/size)));
        Terrain goal = null;
        if (game.getGrass().get(keyEnemy) != null) 
            goal = AStar.aStar(game.getGrass().get(keyEnemy), game.getGrass().get(keyPlayer));
        if (goal != null) System.out.println(goal.getPos().getX());
        //else System.out.println("Hej");
        super.update();
    }
}
