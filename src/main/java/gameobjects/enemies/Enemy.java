package gameobjects.enemies;

import config.Config;
import helperclasses.AStar;
import main.Game;
import mapclasses.Terrain;
import gameobjects.Entity;

import java.util.ArrayList;

public abstract class Enemy extends Entity implements IEnemy {
    private Game game = Game.getInstance();
    private int size = Config.SPRITE_SIZE;
    private int health;
    protected Enemy(int x, int y, double vel,  int health){
        super(x, y, vel, health);
        this.health = health;


    }

    /**
     * Updates the enemy, uses A* algorithm to find the closest path to player,
     * then moves enemy through given path calculated by the A* algorithm
     */
    @Override
    public void update() {
        int size = Config.SPRITE_SIZE;

        String keyPlayer = "" + (int) (size*(Math.round(game.getPlayer().getPos().getX()/size))) +
                (int) (size*(Math.round(game.getPlayer().getPos().getY()/size)));
        String keyEnemy = "" + (int) (size*(Math.round(this.getPos().getX()/size))) +
                (int) (size*(Math.round(this.getPos().getY()/size)));
        Terrain goal = null;
        if (game.getGrass().get(keyEnemy) != null)
            goal = AStar.aStar(game.getGrass().get(keyEnemy), game.getGrass().get(keyPlayer));

        game.setPath(AStar.returnPath(goal));
        super.update();

    }

    /**
     * Method used to move the enemy to target goal.
     * @param goal
     *
     */
    private void moveToGoal(Terrain goal) {
        ArrayList<Terrain> path = game.getPath();
        assert path != null;
        Terrain prevTerrain = null;
        Terrain nextTerrain = null;

        for(int i = 0; i < path.size(); i ++){
            if(i > 0){
                prevTerrain = path.get(i-1);
            }

            nextTerrain = path.get(i);
            moveToTerrain(nextTerrain);
        }


    }

    private void moveToTerrain(Terrain nextStep) {

    }

    private void calculateNextStep() {

    }

    public int getHealth(){
        return health;
    }

    public void damageTaken(int damage) {
        health-= damage;

    }

}
