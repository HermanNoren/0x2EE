package model.gameobjects.ItemSpawner;

import model.gameobjects.IGameObject;
import model.helperclasses.Rect;
import model.helperclasses.Vector2;

public class Potion implements IGameObject {

    private int width, height;
    private Vector2 pos;

    public Potion(Vector2 pos){
        this.pos = pos;
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
}
