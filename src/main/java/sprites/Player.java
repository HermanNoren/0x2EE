package sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements Sprite, MovableSprite{
    private int score;
    private int money;


    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    public Player(int x, int y, int health) throws IOException {
        super(x, y, health);
        setPlayerImages();
        rescaleImages();
        score = 0;
        money = 0;
    }

    /**
     * add the weapon object into the attack.
     */
    @Override
    public void attack() {

    }

    BufferedImage testImage;

    private void rescaleImages() {

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


    /**
     * @param direction Used to move movable sprites.
     *                  Moves the player on the screen.
     */




    private BufferedImage prevImg;

    /**
     * @param g2
     * Updates image according to position and draws the image.
     *
     */
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
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
            g2.drawImage(testImage, (int) getPos().x, (int) getPos().y, null); // Sets default image
        }else {
            g2.drawImage(image, (int)getPos().x,(int) getPos().y, null);

        }
    }
}
