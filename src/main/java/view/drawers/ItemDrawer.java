package view.drawers;

import model.gameinterfaces.IHasItems;
import model.gameobjects.ItemSpawner.IItem;
import view.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDrawer implements IImageIteratorDrawer {

    private IHasItems game;

    private Map<String, BufferedImage> imgs;

    private int index;

    private List<String> itemTypes;

    public ItemDrawer(IHasItems game){
        this.game = game;
        itemTypes = new ArrayList<>();
        itemTypes.add("coin");
        itemTypes.add("potion");
        imgs = new HashMap<>();
        setImgs();
    }

    private void setImgs() {
        for (String itemType : itemTypes) {
            for (int i = 0; i < 4; i++) {
                imgs.put(itemType + i, ImageHandler.getImage("imgs/drops/"+ itemType + "/" + i + ".png"));
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (IItem object : game.getItems()){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(object.getPos(), object.getWidth(), object.getHeight());
            g2.drawImage(imgs.get(object.getType() + index), drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);


        }
    }

    @Override
    public void switchImage() {
        index++;
        index %= 4;
    }
}
