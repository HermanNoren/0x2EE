package sprites;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;

public class Bullet implements ISprite{

    private final int size = Config.SPRITE_SIZE / 2;
    private final Vector2 pos;
    private final Vector2 vel;

    public Bullet(Vector2 pos, Vector2 vel) {
        this.pos = pos;
        this.vel = vel;
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
        return new Vector2(pos);
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.x + (double) (getWidth() / 2);
        double y = pos.y + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public Rect getRect() {
        return null;
    }

    @Override
    public void update() {
        pos.x += vel.x;
        pos.y += vel.y;
    }
}
