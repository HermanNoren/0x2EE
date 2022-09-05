package gamestates;

import sprites.Player;

/**
 * A GameState that implements this interface has a player
 */
public interface GameStateWithPlayer extends GameState {

    /**
     * Returns the player
     * @return Player
     */
    Player getPlayer();

}
