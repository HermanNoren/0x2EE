package model.gameobjects.enemies;

import config.Config;
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

        moveToGoal();
    }



    /**
     * Method used to move the enemy to target goal.
     *
     */
    private void moveToGoal() {



        List<Terrain> path = game.getPath();

        assert path != null;
        for (Terrain terrain: path){
            System.out.println(terrain.getPos().x);
        }
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
