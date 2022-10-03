package view.drawers;

import model.helperclasses.ImageHandler;
import model.gameobjects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Strictly used to draw the player onto the screen
 */
public class PlayerDrawer implements IDrawer {

    private int animationCounter;
    private int imageSwitcher;
    private Player player;

    private ImageHandler imageHandler;

    private BufferedImage prevImg, up1, up2, left1, left2, down1, down2, right1, right2, activeImage;

    private double previousImageSwitchTime, currentTime;

    public PlayerDrawer(Player player) {
        this.player = player;
        this.imageHandler = new ImageHandler();
        initPlayerImages();
        currentTime = System.currentTimeMillis();
        previousImageSwitchTime = System.currentTimeMillis();
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
            up1 = imageHandler.getImage("imgs/player/player_up_1.png");
            up2 = imageHandler.getImage("imgs/player/player_up_2.png");
            left1 = imageHandler.getImage("imgs/player/player_left_1.png");
            left2 = imageHandler.getImage("imgs/player/player_left_2.png");
            down1 = imageHandler.getImage("imgs/player/player_down_1.png");
            down2 = imageHandler.getImage("imgs/player/player_down_2.png");
            right1 = imageHandler.getImage("imgs/player/player_right_1.png");
            right2 = imageHandler.getImage("imgs/player/player_right_2.png");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Clock for switching image, gives an animation effect
     */
    private void movementAnimation() {
        currentTime = System.currentTimeMillis();
        if(currentTime >= previousImageSwitchTime + 250){
            previousImageSwitchTime = currentTime;
            if (imageSwitcher == 1) { imageSwitcher = 2; }
            else { imageSwitcher = 1; }
        }
    }

    /**
     * Draws the player onto the screen in the correct position
     * @param g
     */
    public void draw(Graphics2D g) {
        movementAnimation();
        chooseActiveImage();
        List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(player.getPos(), player.getSize(), player.getSize());
        if(prevImg == null){
            g.drawImage(up1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null); // Sets default image
        }
        else {
            g.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }
        g.setColor(Color.red);
    }
}
