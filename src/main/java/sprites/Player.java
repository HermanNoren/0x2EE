package sprites;

import controllers.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements Sprite, MovableSprite{
    private int score;
    private int money;
    private Direction direction;
    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    public Player(int x, int y, int health) throws IOException {
        super(x, y, health);
        this.direction = Direction.NOT_MOVING;
        setPlayerImages();
        score = 0;
        money = 0;
    }

    /**
     * @throws IOException
     * Sets the player's images.
     */
    private void setPlayerImages() throws IOException {
        up1 = setImage("imgs/player_up_1.png");
        up2 = setImage("imgs/player_up_2.png");
        left1 = setImage("imgs/player_left_1.png");
        left2 = setImage("imgs/player_left_2.png");
        down1 = setImage("imgs/player_down_1.png");
        down2 = setImage("imgs/player_down_2.png");
        right1 = setImage("imgs/player_right_1.png");
        right2 = setImage("imgs/player_right_2.png");

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
    public void move(Direction direction) {
        switch (direction){
            case UP ->{
                this.direction = Direction.UP;
                updatePos(this.direction);
            }
            case LEFT ->{
                this.direction = Direction.LEFT;
                updatePos(this.direction);
            }

            case DOWN -> {
                this.direction = Direction.DOWN;
                updatePos(this.direction);
            }
            case RIGHT -> {
                this.direction = Direction.RIGHT;
                updatePos(this.direction);
            }
            case NOT_MOVING -> this.direction = Direction.NOT_MOVING;
        }
    }

    @Override
    public void setImages(ArrayList<String> imagePath) {

    }

    private BufferedImage prevImg;
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (this.direction){
            case UP -> {
                if(imageSwitcher == 1){
                    image = up1;
                }else if(imageSwitcher == 2){
                    image = up2;
                }prevImg = image;
            }
            case LEFT -> {
                if(imageSwitcher == 1){
                    image = left1;
                }else if(imageSwitcher == 2){
                    image = left2;
                }
                prevImg = image;
            }
            case DOWN -> {
                if(imageSwitcher == 1){
                    image = down1;
                }else if(imageSwitcher == 2){
                    image = down2;
                }
                prevImg = image;
            }
            case RIGHT -> {
                if(imageSwitcher == 1){
                    image = right1;
                }else if(imageSwitcher == 2){
                    image = right2;
                }
                prevImg = image;
            }
            case NOT_MOVING -> image = prevImg;
        }

        if(prevImg == null){
            g2.drawImage(up1, getSize(), getSize(), null); // Sets default image

        }else {
            g2.drawImage(image, getSize(), getSize(), null);
        }
    }
}
