package gamestates;

import main.Game;
import mapclasses.GameMap;
import sprites.Player;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * The GameState that represents the in-game logic
 */
public class InGameState implements GameState{
    private ArrayList<Sprite> sprites;
    private Game game;

    public InGameState(Game game) {
        this.game = game;
        sprites = new ArrayList<>();
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTiles());
    }

    /**
     * Returns an ArrayList containing all the sprites
     * @return All sprites
     */
    @Override
    public ArrayList<Sprite> getSprites() {
        return new ArrayList<>(sprites);
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
