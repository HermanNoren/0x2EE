package sprites;

import controllers.Direction;
import helperclasses.Rect;
import helperclasses.Vector2;
import config.config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Player class, used to create an instance of a playable sprite within the game.
 */
public class Player implements Sprite, MovableSprite {

    private int size = config.SPRITE_SIZE * 3;
    private Rect rect;
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private int health;
    private int score;
    private int money;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    private Direction direction;

    /**
     * @param x, x position of player sprite
     * @param y, y position of player sprite
     */
    public Player(int x, int y) {
        rect = new Rect(x, y, size, size);
        pos = new Vector2(x, y);
        vel = new Vector2(4, 4);
        acc = new Vector2(0, 0);
        health = 100;
        score = 0;
        money = 0;
        direction = Direction.NOT_MOVING;
        setPlayerImages();
    }

    /**
     * Method used to read images from directory.
     */
    private void setPlayerImages() {
        try {
            up1 = ImageIO.read(new File("imgs/player_up_1.png"));
            up2 = ImageIO.read(new File("imgs/player_up_2.png"));
            right1 = ImageIO.read(new File("imgs/player_right_1.png"));
            right2 = ImageIO.read(new File("imgs/player_right_2.png"));
            left1 = ImageIO.read(new File("imgs/player_left_1.png"));
            left2 = ImageIO.read(new File("imgs/player_left_2.png"));
            down1 = ImageIO.read(new File("imgs/player_down_1.png"));
            down2 = ImageIO.read(new File("imgs/player_down_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return score which the player has achieved during the game
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the player health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return currency which the player has accumulated during game
     * Money is used in the ingame shop.
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return width of rectangle surrounding sprite
     */
    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    /**
     * @return height of rectangle surrounding sprite
     */
    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    /**
     * @return position vector
     */
    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * @return rectangle surrounding sprite.
     */
    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    private int imageSwitchCounter = 0;
    private int imageChooser = 0;

    /**
     * Updates player sprite
     */
    @Override
    public void update() {
        animateCounter();
    }

    /**
     * Counter which updates imageChooser twice every second, 200UPS. Creates an animation while walking.
     */
    private void animateCounter() {
        imageSwitchCounter++;
        if (imageSwitchCounter == 100) {
            imageSwitchCounter = 0;
            imageChooser = 1;
        }

        if (imageSwitchCounter >= 50) {
            imageChooser = 2;
        }

    }


    /**
     * @param g2
     * Method used to draw player on view.
     */
    @Override
    public void draw(Graphics2D g2) {
        drawPlayerSprite(g2);
    }

    private BufferedImage prevImage = down1;

    /**
     * @param g2 Draws the player character, switches between images creating animation
     *           and indicating which direction player is moving.
     */
    private void drawPlayerSprite(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case UP -> {
                if (imageChooser == 1) {
                    image = up1;
                } else if (imageChooser == 2) {
                    image = up2;
                }
                prevImage = image;
            }
            case LEFT -> {
                if (imageChooser == 1) {
                    image = left1;

                } else if (imageChooser == 2) {
                    image = left2;
                }
                prevImage = image;
            }
            case RIGHT -> {
                if (imageChooser == 1) {
                    image = right1;
                } else if (imageChooser == 2) {
                    image = right2;
                }
                prevImage = image;
            }
            case DOWN -> {
                if (imageChooser == 1) {
                    image = down1;
                } else if (imageChooser == 2) {
                    image = down2;
                }
                prevImage = image;
            }
            case NOT_MOVING -> image = prevImage;
        }
        if(prevImage == null){
            g2.drawImage(down1, (int)pos.x, (int)pos.y, size, size, null);
        }else {
            g2.drawImage(image, (int)pos.x, (int)pos.y, size, size, null);
        }
    }

    /**
     * @param direction, direction is changed depending on which key is pressed (W A S D)
     * moves the player vel.x or vel.y pixels.
     */
    public void move(Direction direction) {
        switch (direction){
            case NOT_MOVING -> this.direction = Direction.NOT_MOVING;
            case UP -> {
                this.direction = Direction.UP;
                pos.y -= vel.y;
            }
            case LEFT -> {
                this.direction = Direction.LEFT;
                pos.x -= vel.x;
            }
            case DOWN -> {
                this.direction = Direction.DOWN;
                pos.y += vel.y;
            }
            case RIGHT -> {
                this.direction = Direction.RIGHT;
                pos.x += vel.x;
            }
        }
    }
}
