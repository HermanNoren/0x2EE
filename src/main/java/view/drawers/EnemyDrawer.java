package view.drawers;

import sprites.Entity;
import sprites.enemies.Enemy;
import sprites.enemies.IEnemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyDrawer implements IDrawer {
    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2, activeImage;

    private final ArrayList<Enemy> enemies;

    public EnemyDrawer(ArrayList<Enemy> enemies){
        this.enemies = enemies;
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
            up1 = setImage("imgs/enemy_1.png");
            up2 = setImage("imgs/enemy_1.png");
            left1 = setImage("imgs/enemy_1.png");
            left2 = setImage("imgs/enemy_1.png");
            down1 = setImage("imgs/enemy_1.png");
            down2 = setImage("imgs/enemy_1.png");
            right1 =setImage("imgs/enemy_1.png");
            right2 =setImage("imgs/enemy_1.png");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for(Enemy enemy: enemies){
            g2.drawImage(up1, (int)enemy.getPos().x, (int)enemy.getPos().y, enemy.getSize(), enemy.getSize(), null);
        }
    }
}
