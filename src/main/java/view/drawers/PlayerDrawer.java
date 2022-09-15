package view.drawers;

import sprites.Entity;
import view.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Strictly used to draw the player onto the screen
 */
public class PlayerDrawer implements IDrawer {

    private int animationCounter;
    private int imageSwitcher;
    private Entity player;

    private Camera camera;

    private BufferedImage prevImg, up1, up2, left1, left2, down1, down2, right1, right2, activeImage;

    public PlayerDrawer(Entity player, Camera camera) {
        this.player = player;
        this.camera = camera;
        initPlayerImages();
    }

    /**
     * Draws the player onto the screen in the correct position
     * @param g
     */
    public void draw(Graphics2D g) {
        movementAnimation();
        chooseActiveImage();
        if(prevImg == null){
            g.drawImage(up1, (int) (player.getPos().x - camera.getOffset().x), (int) (player.getPos().y - camera.getOffset().y), player.getSize(), player.getSize(), null); // Sets default image
        }
        else {
            g.drawImage(activeImage, (int) (player.getPos().x - camera.getOffset().x), (int) (player.getPos().y - camera.getOffset().y), player.getSize(), player.getSize(), null);
        }
        g.setColor(Color.red);
    }

    /**
     * Used to choose the correct image to use for the drawing of the player
     */
    private void chooseActiveImage() {
        switch (player.getDirection()){
            case UP -> {
                if(imageSwitcher == 1){
                    activeImage = up1;
                }
                else if(imageSwitcher == 2){
                    activeImage = up2;
                }
                prevImg = activeImage;
            }
            case LEFT -> {
                if(imageSwitcher == 1){
                    activeImage = left1;
                }
                else if(imageSwitcher == 2){
                    activeImage = left2;
                }
                prevImg = activeImage;
            }
            case DOWN -> {
                if(imageSwitcher == 1){
                    activeImage = down1;
                }
                else if(imageSwitcher == 2){
                    activeImage = down2;
                }
                prevImg = activeImage;
            }
            case RIGHT -> {
                if(imageSwitcher == 1){
                    activeImage = right1;
                }
                else if(imageSwitcher == 2){
                    activeImage = right2;
                }
                prevImg = activeImage;
            }
            case NOT_MOVING -> activeImage = prevImg;
        }
    }

    /**
     * Initializes all the images used to draw the player
     */
    private void initPlayerImages(){
        try {
            up1 = setImage("imgs/player_up_1.png");
            up2 = setImage("imgs/player_up_2.png");
            left1 = setImage("imgs/player_left_1.png");
            left2 = setImage("imgs/player_left_2.png");
            down1 = setImage("imgs/player_down_1.png");
            down2 = setImage("imgs/player_down_2.png");
            right1 = setImage("imgs/player_right_1.png");
            right2 = setImage("imgs/player_right_2.png");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fetches an image from the given path
     * @param path path to the image
     * @return image
     */
    private BufferedImage setImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    /**
     * Clock for switching image, gives an animation effect
     */
    private void movementAnimation() {
        animationCounter++;
        if(animationCounter > 100){
            imageSwitcher = 1;
            animationCounter = 0;
        }else if(animationCounter == 50){
            imageSwitcher = 2;
        }
    }
}
