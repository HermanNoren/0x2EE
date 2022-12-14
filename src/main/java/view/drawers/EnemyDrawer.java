package view.drawers;

import model.gameinterfaces.IHasEnemies;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.EDirection;
import view.ImageHandler;


import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.*;
import java.util.List;

/**
 * Draws all types of enemies.
 * @author Rickard Leksell
 */
public class EnemyDrawer implements IImageIteratorDrawer {
    private final List<EDirection> directions;
    private final IHasEnemies game;
    private final List<String> imgTypes;
    private Map<EDirection, Map<String, BufferedImage>> imgs;
    private int index;

    public EnemyDrawer(IHasEnemies game){
        this.game = game;
        imgs = new HashMap<>();
        imgTypes = new ArrayList<>();
        imgTypes.add("normal");
        imgTypes.add("boss");
        directions = new ArrayList<>();
        directions.add(EDirection.UP);
        directions.add(EDirection.LEFT);
        directions.add(EDirection.DOWN);
        directions.add(EDirection.RIGHT);
        initImages();
    }

    private void initImages(){
        imgs = ImageHandler.getImgsWithDirections(2, imgTypes, directions);
    }

    /**
     * Draws/updates enemy images on screen.
     * @param g2 see Graphics2D java
     */
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img;
        for (Enemy enemy: game.getEnemies()){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(enemy.getPos(), enemy.getWidth(), enemy.getWidth());
            if (enemy.getHealth() != enemy.getMaxHp()){
                g2.setColor(Color.red);
                g2.fillRect(drawInformation.get(0), drawInformation.get(1) - 6, (int) (drawInformation.get(2) - (drawInformation.get(2) * (1 - enemy.getHealth() / (enemy).getMaxHp()))), 4);
                g2.setColor(Color.black);
                g2.drawRoundRect(drawInformation.get(0), drawInformation.get(1) - 6, (int) (drawInformation.get(2) - (drawInformation.get(2) * (1 - enemy.getHealth() / (enemy).getMaxHp()))), 4, 0,0);
            }
            if(enemy.getDirection() == EDirection.NOT_MOVING){
                img = imgs.get(enemy.getLastDirection()).get(enemy.getType().toString().toLowerCase() + 1);
            }
            else{
                img = imgs.get(enemy.getDirection()).get(enemy.getType().toString().toLowerCase() + index);
            }
            g2.drawImage(img, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }

    }

    @Override
    public void switchImage() {
        index++;
        index %= 2;
    }
}
