package sprites;

import helperclasses.Rect;
import helperclasses.Vector2;
import config.config;

import java.awt.*;

public class Player implements Sprite {

    private int size = config.SPRITE_SIZE * 3;
    private Rect rect;
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    public Player(int x, int y) {
        rect = new Rect(x, y, size, size);
        pos = new Vector2(x, y);
        vel = new Vector2(5, 5);
        acc = new Vector2(0, 0);
    }

    @Override
    public int getWidth() {
        return rect.getWidth();
    }

    @Override
    public int getHeight() {
        return rect.getHeight();
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public void update() {
        pos.x += vel.x;
        pos.y += vel.y;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int) pos.x, (int) pos.y, size, size);
    }

}
