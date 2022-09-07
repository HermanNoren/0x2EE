package gamestates;

import sprites.Player;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * A GameState that implements this interface has a player
 */
public interface GameStateWithPlayer extends GameState {

    /**
     * Returns the player
     * @return Player
     */
    Player getPlayer();

    ArrayList<Sprite> getTiles();
}
