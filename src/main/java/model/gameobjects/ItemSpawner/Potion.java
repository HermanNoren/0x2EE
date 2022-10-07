package model.gameobjects.ItemSpawner;

import model.gameobjects.Player;
import model.helperclasses.Vector2;

public class Potion implements IItem {

    private int width, height;
    private Vector2 pos;

    public Potion(Vector2 pos){
        this.pos = pos;
        width = 20;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public void consume(Player player) {
        player.setHealth(player.getHealth() + 300);
    }
}
