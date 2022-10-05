package Model.gameobjects.ItemSpawner;

import Model.gameobjects.Player;
import Model.helperclasses.Vector2;

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
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getCenter() {
        return null;
    }
    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void consume(Player player) {
        player.setHealth(player.getHealth() + 300);
    }
}
