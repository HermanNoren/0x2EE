package view.drawers;

import model.helperclasses.EDirection;
import view.ImageHandler;
import model.gameobjects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Strictly used to draw the player onto the screen
 */
public class PlayerDrawer implements IImageIteratorDrawer {
    private int imageSwitcher;
    private Player player;

    private Map<EDirection, Map<String, BufferedImage>> imgs;
    private List<String> imgTypes;
    private int index;

    public PlayerDrawer(Player player) {
        this.player = player;
        imgTypes = new ArrayList<>();
        imgs = new HashMap<>();
        imgTypes.add("player");
        initPlayerImages();
    }



    /**
     * Initializes all the images used to draw the player
     */
    private void initPlayerImages(){
        ImageHandler.setImgsWithDirections(2, imgs, imgTypes, EDirection.up);
        ImageHandler.setImgsWithDirections(2, imgs, imgTypes, EDirection.left);
        ImageHandler.setImgsWithDirections(2, imgs, imgTypes, EDirection.down);
        ImageHandler.setImgsWithDirections(2, imgs, imgTypes, EDirection.right);
    }

    /**
     * Draws the player onto the screen in the correct position
     * @param g
     */
    public void draw(Graphics2D g) {
        BufferedImage img;
        List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(player.getPos(), player.getWidth(), player.getWidth());
        if (player.getDirection() == EDirection.not_moving){
            img = imgs.get(player.getLastDirection()).get("player"+1);
        }
        else {
            img = imgs.get(player.getDirection()).get("player"+index);
        }
        g.drawImage(img, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);



    }

    @Override
    public void switchImage() {
        index++;
        index %=2;
    }
}
