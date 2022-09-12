package gamestates;

import main.Game;
import sprites.ISprite;

import java.util.ArrayList;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<ISprite> sprites;
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
        for (ISprite sprite : sprites) {
            sprite.update();
        }
    }
}
