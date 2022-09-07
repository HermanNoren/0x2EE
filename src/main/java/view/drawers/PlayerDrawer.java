package view.drawers;

import controllers.Direction;
import sprites.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerDrawer implements Drawer{

    int animationCounter;
    int imageSwitcher;
    Entity player;

    private BufferedImage prevImg;

    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public PlayerDrawer(Entity player) {
        this.player = player;
        setPlayerImages();
    }

    public void draw(Graphics2D g) {
        movementAnimation();
        BufferedImage image = null;
        switch (player.getDirection()){
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
            g.drawImage(up1, (int) player.getPos().x, (int) player.getPos().y, null); // Sets default image
        }else {
            g.drawImage(image, (int) player.getPos().x,(int) player.getPos().y, null);

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
