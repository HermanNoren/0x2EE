package gameobjects.enemies;

import config.Config;
import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.enemies.IEnemy;
import model.mapclasses.Terrain;

abstract class Enemy extends Entity implements IEnemy {
    private Game game = Game.getInstance();
    private int size = Config.SPRITE_SIZE;
    protected Enemy(int x, int y, double vel,  int health){
        super(x, y, vel, health);


    }

    /**
     * Updates the enemy, uses A* algorithm to find the closest path to player,
     * then moves enemy through given path calculated by the A* algorithm
     */
    @Override
    public void update() {
        int size = Config.SPRITE_SIZE;
        super.update();

    }

    /**
     * Method used to move the enemy to target goal.
     * @param goal
     *
     */
    private void moveToGoal(Terrain goal) {
    }

    private void moveToTerrain(Terrain nextStep) {

    }

    private void calculateNextStep() {

    }

}
