package view.drawers;


import model.gameinterfaces.IHasEnemies;
import model.gameobjects.Entity;
import view.ImageHandler;


import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.List;

public class EnemyDrawer implements IDrawer, IIteratedImageDrawer {
    private BufferedImage prevImg, up1, up2, left1, left2, down1, down2, right1, right2, activeImage;
    private final IHasEnemies game;
    private int imageSwitcher;
    private ImageHandler imageHandler;

    public EnemyDrawer(IHasEnemies game, String type){
        this.game = game;
        this.imageHandler = new ImageHandler();
        initImages(type);
    }

    private void initImages(String type){
        up1 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_up_1.png");
        up2 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_up_2.png");
        left1 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_left_1.png");
        left2 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_left_2.png");
        down1 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_down_1.png");
        down2 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_down_2.png");
        right1 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_right_1.png");
        right2 = imageHandler.getImage("imgs/enemy/"+type+"/enemy_right_2.png");
    }

    /**
     * Draws/updates enemy images on screen.
     * @param g2
     *
     */
    @Override
    public void draw(Graphics2D g2) {
        for(Entity enemy: game.getEnemies()){
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

            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(enemy.getPos(), enemy.getWidth(), enemy.getWidth());
            if (enemy.getHealth() != enemy.getMaxHp()){
                g2.setColor(Color.red);
                g2.fillRect(drawInformation.get(0), drawInformation.get(1) - 6, (int) (drawInformation.get(2) - (drawInformation.get(2) * (1 - enemy.getHealth() / (enemy).getMaxHp()))), 4);
                g2.setColor(Color.black);
                g2.drawRoundRect(drawInformation.get(0), drawInformation.get(1) - 6, (int) (drawInformation.get(2) - (drawInformation.get(2) * (1 - enemy.getHealth() / (enemy).getMaxHp()))), 4, 0,0);
            }
            if(!(prevImg == null)){
                g2.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
            else {
                g2.drawImage(up1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                g2.drawImage(activeImage, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
            }
        }
    }

    @Override
    public void switchImage() {
        if (imageSwitcher == 1) { imageSwitcher = 2; }
        else { imageSwitcher = 1; }
    }
}
