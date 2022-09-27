package model.gameobjects.ItemSpawner;

import model.gameobjects.IGameObject;
import model.gameobjects.Player;
import model.helperclasses.Rect;
import model.helperclasses.Vector2;

public class Coin implements IItem {

    private int width, height;
    private Vector2 pos;

    public Coin(Vector2 pos){
        this.pos = pos;
        width = 10;
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
    public Rect getRect() {
        return null;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void update() {

    }
    @Override
    public void consume(Player player) {
        player.addMoney(10);
    }
}
