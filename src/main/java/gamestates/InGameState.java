package gamestates;

import view.Observer;
import view.HUD;
import sprites.Player;
import sprites.Sprite;
import mapclasses.Map;

import java.util.ArrayList;

/**
 * The GameState that represents the in-game logic
 */
public class InGameState implements GameStateWithPlayer{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;

    public InGameState() {
        player = new Player(0, 0, 100);
        sprites = new ArrayList<>();
        map = new Map();
        sprites.add(player);
        sprites.addAll(map.getTiles());
    }

    /**
     * Returns the instance of the player
     * @return player
     */
    @Override
    public Player getPlayer() {
        return player;
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

    @Override
    public void draw() {

    }

}
