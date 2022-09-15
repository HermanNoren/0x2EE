package view.drawers;

import sprites.enemies.IEnemy;
import view.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyDrawer implements IDrawer {
    private BufferedImage prevImg, up1, up2, left1, left2, down1, down2, right1, right2, activeImage;
    private final ArrayList<IEnemy> enemies;
    private int animationCounter;
    private int imageSwitcher;

    private Camera camera;

    public EnemyDrawer(ArrayList<IEnemy> enemies, Camera camera){
        this.enemies = enemies;
        this.camera = camera;
        initEnemyImages();

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

    private void initEnemyImages(){
        try {
            up1 = setImage("imgs/enemy_up_1.png");
            up2 = setImage("imgs/enemy_up_2.png");
            left1 = setImage("imgs/enemy_left_1.png");
            left2 = setImage("imgs/enemy_left_2.png");
            down1 = setImage("imgs/enemy_down_1.png");
            down2 = setImage("imgs/enemy_down_2.png");
            right1 =setImage("imgs/enemy_right_1.png");
            right2 =setImage("imgs/enemy_right_2.png");
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
        for(IEnemy enemy: enemies){
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

            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(enemy.getPos(), enemy.getSize(), enemy.getSize(), camera);

            if(!(prevImg == null)){
                g2.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
            else {
                g2.drawImage(up1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
        }
    }
}
