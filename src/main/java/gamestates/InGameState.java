package gamestates;

import main.Game;
import sprites.ISprite;

import view.panelstates.EPanelState;

import java.util.ArrayList;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<ISprite> sprites;
    private Game game;
    private final EPanelState stateTag = EPanelState.INGAME;

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
    public EPanelState getStateTag() {
        return stateTag;
    }

    /**
     * Updates all the in-game objects
     */
    @Override
    public void update() {
        if (game.getEscapePressed()) {
            game.resetEscapePressed();
            game.setState(new PauseState(game));
        }

        for (ISprite sprite : sprites) {
            sprite.update();
        }
    }
}
