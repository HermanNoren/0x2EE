package sprites;

import config.Config;
import controllers.Direction;
import helperclasses.Rect;
import helperclasses.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Entity class contains logic to represent the sprite,
 * for example by containing information regarding its position
 * and health the program can determine if an entity is in range
 * to take damage thus if reduces its health.
 */

public abstract class Entity implements Sprite {
    int animationCounter;
    Vector2 pos;
    Vector2 vel;
    Vector2 acc;
    int health;
    private Direction direction;
    private int size = Config.SPRITE_SIZE * 3;
    private Rect rect;


    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     * @param health
     */

    public Entity(int x, int y, int health){
        this.direction = Direction.NOT_MOVING; // Default value
        this.pos = new Vector2(x, y);
        this.vel = new Vector2(0.5, 0.5);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, size, size);
        this.health = health;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void updatePos(){
        if(!(direction == Direction.NOT_MOVING)){
            switch (direction){
                case UP -> pos.y -= vel.y;
                case LEFT -> pos.x -= vel.x;
                case DOWN -> pos.y += vel.y;
                case RIGHT -> pos.x += vel.x;
            }
        }
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
     * Updates the entites location by adding its coordinate with its velocity
     */
    @Override
    public void update() {
        updatePos();
    }
    public abstract int damageDelt();

    /**
     * The same argument can be said regarding the
     * damage taken.
     *
     */
    public abstract void damageTaken(int damage);

    public int getSize() {
        return size;
    }
}
