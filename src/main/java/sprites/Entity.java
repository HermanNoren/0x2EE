package sprites;

import config.config;
import helperclasses.Rect;
import helperclasses.Vector2;

import java.awt.*;

/**
 * The Entity class contains logic to represent the sprite,
 * for example by containing information regarding its position and health
 * the program can determine if an entity is in range to take damage thus
 * if reduces its health.
 */

public abstract class Entity implements Sprite {

    Vector2 pos;
    Vector2 vel;
    Vector2 acc;
    int health;
    private int size = config.SPRITE_SIZE * 3;
    private Rect rect;

    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     * @param health
     */



    public Entity(int x, int y, int health){
        pos = new Vector2(x, y);
        vel = new Vector2(1, 1);
        acc = new Vector2(0, 0);
        rect = new Rect(x, y, size, size);
        this.health = health;
    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return new Rect(this.rect);
    }

    /**
     * Updates the entites' location by adding its coordinate with its velocity
     */
    @Override
    public void update() {
        pos.x += vel.x;
        pos.y += vel.y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) pos.x, (int) pos.y, size, size);
    }
}
