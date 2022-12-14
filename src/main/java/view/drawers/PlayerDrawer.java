package view.drawers;

import model.gameobjects.EDirection;
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
 * @author Arthur Alexandersson, Herman Noren
 */
public class PlayerDrawer implements IImageIteratorDrawer {
    private Player player;
    private Map<EDirection, Map<String, BufferedImage>> imgs;
    private List<String> imgTypes;
    private List<EDirection> directions;
    private int index;

    public PlayerDrawer(Player player) {
        this.player = player;
        imgTypes = new ArrayList<>();
        imgs = new HashMap<>();
        imgTypes.add("player");
        directions = new ArrayList<>();
        directions.add(EDirection.RIGHT);
        directions.add(EDirection.UP);
        directions.add(EDirection.LEFT);
        directions.add(EDirection.DOWN);
        initPlayerImages();
    }



    /**
     * Initializes all the images used to draw the player
     */
    private void initPlayerImages(){
        imgs = ImageHandler.getImgsWithDirections(2, imgTypes, directions);
    }

    /**
     * Draws the player onto the screen in the correct position
     * @param g
     */
    public void draw(Graphics2D g) {
        BufferedImage img;
        List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(player.getPos(), player.getWidth(), player.getWidth());
        if (player.getDirection() == EDirection.NOT_MOVING){
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
