package model.gameobjects.enemies;

import config.Config;
import model.helperclasses.AStar;
import model.Game;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.Entity;

import java.util.ArrayList;

public abstract class Enemy extends Entity implements IEnemy {

    private int size = Config.SPRITE_SIZE;
    protected Enemy(int x, int y, Game game){
        super(x, y, game);
    }

    /**
     * Updates the enemy, uses A* algorithm to find the closest path to player,
     * then moves enemy through given path calculated by the A* algorithm
     */
    @Override
    public void update() {

    }

    /**
     * Method used to move the enemy to target goal.
     * @param goal
     *
     */
    private void moveToGoal(Terrain goal) {
//        ArrayList<Terrain> path = game.getPath();
//        assert path != null;
//        Terrain prevTerrain = null;
//        Terrain nextTerrain = null;
//
//        for(int i = 0; i < path.size(); i ++){
//            if(i > 0){
//                prevTerrain = path.get(i-1);
//            }
//
//            nextTerrain = path.get(i);
//            moveToTerrain(nextTerrain);
//        }


    }

}
