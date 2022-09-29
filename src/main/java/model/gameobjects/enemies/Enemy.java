package model.gameobjects.enemies;

import config.Config;
import controllers.EDirection;
import model.gameobjects.Player;
import model.helperclasses.AStar;
import model.Game;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entity implements IEnemy {

    private int size = Config.SPRITE_SIZE;
    private Game game;
    protected Enemy(int x, int y, Game game){
        super(x, y, game);
        this.game = game;
    }



    /**
     * Method used to move the enemy to target goal.
     *
     */
    public void moveToGoal() {
        Player player = game.getPlayer();
        Terrain goal = player.getMapLocation();
        Terrain current = getMapLocation();

        int goalX = goal.getX();
        int goalY = goal.getY();

        int currentX = current.getX();
        int currentY = current.getY();

        if (currentX < goalX){
            setDirection(EDirection.RIGHT);
        }else if(currentX > goalX){
            setDirection(EDirection.LEFT);
        } else if (currentY < goalY) {
            setDirection(EDirection.DOWN);
        } else if (currentY > goalY) {
            setDirection(EDirection.UP);
        }

    }

    @Override
    public void update() {
//        moveToGoal();
    }
}
