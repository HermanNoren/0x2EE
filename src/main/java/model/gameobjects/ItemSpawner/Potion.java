package model.gameobjects.ItemSpawner;

import config.Config;
import model.gameobjects.Player;
import model.Vector2;

/**
 * Class for representing the game item potion. This class handles what the result should be when the player interacts with it.
 * @author Rickard Leksell
 */
public class Potion implements IItem {

    private Vector2 pos;

    private int size = Config.SPRITE_SIZE*2;

    public Potion(Vector2 pos){
        this.pos = pos;
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }


    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public String getType() {
        return "potion";
    }


    /**
     * Handles the interaction between the player and a certain item.
     * @param player the player that uses an item
     */
    @Override
    public void consume(Player player) {
        player.setHealth(player.getHealth() + 300);
    }
}
