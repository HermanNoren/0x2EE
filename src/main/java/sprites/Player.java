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

public class Player implements Sprite {

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
        vel = new Vector2(5, 5);
        acc = new Vector2(0, 0);
        health = 100;
        score = 0;
        money = 0;
        direction = Direction.UP;
        getPlayerImg();

    }

    public void getPlayerImg(){
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

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction){
            case UP -> image = up1;
            case LEFT -> image = left1;
            case RIGHT -> image = right1;
            case DOWN -> image = down1;
        }


        g2.drawImage(image, (int)pos.x, (int)pos.y, size, size, null);

    }

    public void move(Direction direction) {
        switch (direction){
            case NEUTRAL -> {
            }
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
