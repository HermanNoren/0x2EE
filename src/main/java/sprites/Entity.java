package sprites;

import config.config;
import controllers.Direction;
import helperclasses.Rect;
import helperclasses.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The Entity class contains logic to represent the sprite,
 * for example by containing information regarding its position and health
 * the program can determine if an entity is in range to take damage thus
 * if reduces its health.
 */

public abstract class Entity implements Sprite {
    int animationCounter;
    int imageSwitcher;
    Vector2 pos;
    Vector2 vel;
    Vector2 acc;
    int health;
    private BufferedImage image;
    Direction direction;
    private int size = config.SPRITE_SIZE * 3;
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
        this.vel = new Vector2(1, 1);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, size, size);
        this.health = health;
    }

    public BufferedImage setImage(String path) throws IOException {
        BufferedImage image;
        image = ImageIO.read(new File(path));
        return image;
    }
    public void updatePos(Direction direction){
        System.out.println(pos.y);
        switch (direction){
            case UP -> pos.y -= vel.y;
            case LEFT -> pos.x -= vel.x;
            case DOWN -> pos.y += vel.y;
            case RIGHT -> pos.x += vel.x;
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
     * Updates the entites' location by adding its coordinate with its velocity
     */
    @Override
    public void update() {
        animationCounter++;
        if(animationCounter > 100){
            imageSwitcher = 1;
            animationCounter = 0;
            System.out.println(imageSwitcher);
        }else if(animationCounter == 50){
            imageSwitcher = 2;
            System.out.println(imageSwitcher);
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public abstract void draw(Graphics2D g2);

}
