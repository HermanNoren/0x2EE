package gamestates;

import main.Game;
import sprites.ISprite;

import view.panelstates.EStateTag;

import java.util.ArrayList;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<ISprite> sprites;
    private Game game;
    private final EStateTag stateTag = EStateTag.INGAME;

    public InGameState(Game game) {
        this.game = game;
        sprites = new ArrayList<>();
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTiles());
    }



    /**
     * Returns the specific state tag
     * @return stateTag
     */
    @Override
    public EStateTag getStateTag() {
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
