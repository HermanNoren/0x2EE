package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.Sprite;

import java.awt.*;

public class Tile implements Sprite {
    private int size = Config.SPRITE_SIZE;
    private Vector2 pos;
    private Rect rect;

    public Tile(int x, int y) {
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

    /*
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect((int) pos.x, (int) pos.y, size, size);
    }

     */
}