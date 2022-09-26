package view.drawers;


import model.gameobjects.Entity;
import model.gameobjects.enemies.IEnemy;
import model.helperclasses.ImageHandler;


import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

public class EnemyDrawer implements IDrawer {
    private BufferedImage prevImg, up1, up2, left1, left2, down1, down2, right1, right2, activeImage;
    private final List<Entity> enemies;
    private int animationCounter;
    private int imageSwitcher;

    private ImageHandler imageHandler;

    public EnemyDrawer(List<Entity> enemies){
        this.enemies = enemies;
        this.imageHandler = new ImageHandler();
        initEnemyImages();

    }

    private void initEnemyImages(){
        try {
            up1 = imageHandler.getImage("imgs/enemy/enemy_up_1.png");
            up2 = imageHandler.getImage("imgs/enemy/enemy_up_2.png");
            left1 = imageHandler.getImage("imgs/enemy/enemy_left_1.png");
            left2 = imageHandler.getImage("imgs/enemy/enemy_left_2.png");
            down1 = imageHandler.getImage("imgs/enemy/enemy_down_1.png");
            down2 = imageHandler.getImage("imgs/enemy/enemy_down_2.png");
            right1 = imageHandler.getImage("imgs/enemy/enemy_right_1.png");
            right2 = imageHandler.getImage("imgs/enemy/enemy_right_2.png");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
    /**
     * Draws/updates enemy images on screen.
     * @param g2
     *
     */
    @Override
    public void draw(Graphics2D g2) {

        movementAnimation();
        for(Entity enemy: enemies){
            switch (enemy.getDirection()){
                case UP ->{
                    if(imageSwitcher == 1){
                        activeImage = up1;
                    }else {
                        activeImage = up2;
                    }
                    prevImg = activeImage;
                }
                case LEFT -> {
                    if(imageSwitcher == 1){
                        activeImage = left1;
                    }else {
                        activeImage = left2;
                    }
                    prevImg = activeImage;
                }
                case DOWN -> {
                    if(imageSwitcher == 1){
                        activeImage = down1;
                    }else {
                        activeImage = down2;
                    }
                    prevImg = activeImage;
                }
                case RIGHT -> {
                    if(imageSwitcher == 1){
                        activeImage = right1;
                    }else {
                        activeImage = right2;
                    }
                    prevImg = activeImage;
                }
                case NOT_MOVING -> {
                    activeImage = prevImg;
                }
            }

            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(enemy.getPos(), enemy.getSize(), enemy.getSize());

            if(!(prevImg == null)){
                g2.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
            else {
                g2.drawImage(up1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                g2.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
        }
    }
}
