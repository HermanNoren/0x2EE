package sprites;

import armor.Armor;
import controllers.Direction;
import org.imgscalr.Scalr;
import weapons.Pistol;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements Sprite, MovableSprite{
    private int score;
    private int money;
    protected Pistol pistol;

    protected Armor armor;


    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    private List<BufferedImage> images;

    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * @param health, starting health
     * Player constructor, used to create an instance of player.
     */
    public Player(int x, int y, int health){
        super(x, y, health);
        images = new ArrayList<>();
        setPlayerImages();
        rescaleImages();
        score = 0;
        money = 0;
    }

    /**
     * add the weapon object into the attack.
     */
    public int damageDelt() {
        return this.pistol.damage;
    }

    public void damageTaken(int damage) {

    }

    private void rescaleImages() {

    }

    /**
     * Sets the player's images, and rescales according to entity size.
     */
    private void setPlayerImages(){
//        up1 = Scalr.resize(setImage("imgs/player_up_1.png"), getSize());
//        up2 = Scalr.resize(setImage("imgs/player_up_2.png"), getSize());
//        left1 = Scalr.resize(setImage("imgs/player_left_1.png"), getSize());
//        left2 = Scalr.resize(setImage("imgs/player_left_2.png"), getSize());
//        down1 = Scalr.resize(setImage("imgs/player_down_1.png"), getSize());
//        down2 = Scalr.resize(setImage("imgs/player_down_2.png"), getSize());
//        right1 =Scalr.resize(setImage("imgs/player_right_1.png"), getSize());
//        right2 =Scalr.resize(setImage("imgs/player_right_2.png"), getSize());

        up1 = setImage("imgs/player_up_1.png");
        up2 = setImage("imgs/player_up_2.png");
        left1 = setImage("imgs/player_left_1.png");
        left2 = setImage("imgs/player_left_2.png");
        down1 = setImage("imgs/player_down_1.png");
        down2 = setImage("imgs/player_down_2.png");
        right1 =setImage("imgs/player_right_1.png");
        right2 =setImage("imgs/player_right_2.png");
    }

    /**
     * @return score acquired during game
     */
    public int getScore(){
        return score;
    }

    /**
     * @return player's health
     */
    public int getHealth(){
        return health;
    }

    /**
     * @return currency acquired during game
     */
    public int getMoney(){
        return money;
    }

    private BufferedImage prevImg;

    /**
     * @param g2
     * Updates image according to position and draws the image.
     */
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        drawRect(g2);
        switch (getDirection()){
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
            g2.drawImage(up1, (int) getPos().x, (int) getPos().y, getSize(), getSize(), null); // Sets default image
        }else {
            g2.drawImage(image, (int)getPos().x,(int) getPos().y, getSize(), getSize(), null);

        }
    }
    public void drawRect(Graphics2D g2){
        g2.drawRect((int)getPos().x, (int)getPos().y, getWidth(), getHeight());
    }
}
