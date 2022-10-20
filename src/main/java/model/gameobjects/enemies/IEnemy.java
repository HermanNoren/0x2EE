package model.gameobjects.enemies;

import model.gameobjects.Entity;

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
    int getKillReward();

    /**
     * Updates the enemy with an improvement of dt.
     * @param dt the delta time from previous update
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
