package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.ISprite;

public class Terrain implements ISprite {

    private final int size = Config.SPRITE_SIZE;
    private Vector2 pos;
    private Rect rect;

    public Terrain(int x, int y) {
        pos = new Vector2(x, y);
        rect = new Rect(x, y, size, size);
    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public void update() {

    }
}