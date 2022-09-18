package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.ISprite;

public class Tile implements ISprite {

    private final int size = Config.WALL_SIZE;
    private Vector2 pos;

    private CollidableObject collidableObject;
    private Rect rect;

    public Tile(int x, int y, boolean isCollidable) {
        pos = new Vector2(x, y);
        rect = new Rect(x, y, size, size);
        collidableObject.isCollidable = isCollidable;
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
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public void update() {

    }
}