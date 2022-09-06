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
 * Player class
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

    public Player(int x, int y){
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

    private void setPlayerImages(){
        try{
            up1 = ImageIO.read(new File("imgs/player_up_1.png"));
            up2 = ImageIO.read(new File("imgs/player_up_2.png"));
            right1 = ImageIO.read(new File("imgs/player_right_1.png"));
            right2 = ImageIO.read(new File("imgs/player_right_2.png"));
            left1 = ImageIO.read(new File("imgs/player_left_1.png"));
            left2 = ImageIO.read(new File("imgs/player_left_2.png"));
            down1 = ImageIO.read(new File("imgs/player_down_1.png"));
            down2 = ImageIO.read(new File("imgs/player_down_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getScore(){
        return score;
    }

    public int getHealth(){
        return health;
    }

    public int getMoney(){
        return money;
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
        return new Rect(rect);
    }

    private int imageSwitchCounter = 0;
    private int imageChooser = 0;

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


    @Override
    public void draw(Graphics2D g2) {
        drawPlayerCharacter(g2);
    }

    private BufferedImage prevImage = null;

    /**
     * @param g2
     * Draws the player character, switches between images creating animation
     *      and indicating which direction player is moving.
     */
    private void drawPlayerCharacter(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction){
            case UP -> {
                if (imageChooser == 1){
                    image = up1;
                }else if(imageChooser == 2){
                    image = up2;
                }
                prevImage = image;
            }
            case LEFT -> {
                if (imageChooser == 1){
                    image = left1;

                }else if(imageChooser == 2){
                    image = left2;
                }
                prevImage = image;
            }
            case RIGHT -> {
                if (imageChooser == 1){
                    image = right1;
                }else if(imageChooser == 2){
                    image = right2;
                }
                prevImage = image;
            }
            case DOWN ->{
                if (imageChooser == 1){
                    image = down1;
                }else if (imageChooser == 2){
                    image = down2;
                }
                prevImage = image;
            }
            case NOT_MOVING -> {
                if(image == null){ // Starting image.
                    image = down1;
                } else {
                    image = prevImage;
                }
            }
        }

        g2.drawImage(image, (int)pos.x, (int)pos.y, size, size, null);
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
