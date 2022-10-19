package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.mapclasses.Tile;

import java.util.PriorityQueue;

/**
 * Interface for IEnemy which encapsulated methods to create better abstraction
 * through limitation.
 */
public interface IEnemy{
    /**
     * @return The enemy type.
     */
    String getType();

    /**
     * @return The enemy damage.
     */
    int getDamage();

    /**
     * @return The score reward for killing an enemy.
     */
    int getSCoreReward();

    /**
     * Updates the enemy with an improvement of dt.
     * @param dt
     */
    void update(double dt);

    /**
     * The entity which the enemy is chasing.
     * @return
     */
    Entity getTargetEntity();

    /**
     * AStar class contains a static method with the AStar algorithm.
     */

}
