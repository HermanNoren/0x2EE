package model.gameinterfaces;

import model.gameobjects.Player;

public interface IHasPlayer {

    /**
     * Returns the player of the game
     * @return player
     */
    Player getPlayer();

    /**
     * Returns true if player has died, else returns false
     * @return Alive status of player
     */
    boolean isPlayerDead();

    /**
     * Returns true if a player score is among the top 5 best scores, else returns false
     * @return if a score is among the best 5
     */
    boolean isTopFive();

}
