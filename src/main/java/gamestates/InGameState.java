package gamestates;

import main.Game;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * The GameState that represents the in-game logic
 */
public class InGameState implements GameState{
    private ArrayList<Sprite> sprites;
    private Game game;
    private final String stateTag = "InGame";

    public InGameState(Game game) {
        this.game = game;
        sprites = new ArrayList<>();
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTiles());
    }

    @Override
    public String getStateTag() {
        return stateTag;
    }

    /**
     * Updates all the in-game objects
     */
    @Override
    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }
}
