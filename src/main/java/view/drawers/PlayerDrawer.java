package view.drawers;

import config.Config;
import sprites.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerDrawer implements SpriteDrawer {

    int animationCounter;
    int imageSwitcher;
    Entity player;

    private BufferedImage prevImg;

    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2, activeImage;

    public PlayerDrawer(Entity player) {
        this.player = player;
        setPlayerImages();
    }

    public void draw(Graphics2D g) {
        movementAnimation();
        chooseActiveImage();
        if(prevImg == null){
            g.drawImage(up1, (int) player.getPos().x, (int) player.getPos().y, player.getSize(), player.getSize(), null); // Sets default image
        }
        else {
            g.drawImage(activeImage, (int) player.getPos().x,(int) player.getPos().y, player.getSize(), player.getSize(), null);

        }
    }

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

    private void setPlayerImages(){
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

    private BufferedImage setImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

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
