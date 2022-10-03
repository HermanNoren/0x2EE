package view.drawers;

import model.gameobjects.IGameObject;
import model.gameobjects.ItemSpawner.IItem;
import model.helperclasses.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemDrawer implements IDrawer{

    private List<IItem> objects;

    private ImageHandler imageHandler;

    private BufferedImage potion, coinFront, coinBack, coinSide;

    private int counter;

    public ItemDrawer(List<IItem> objects){
        this.objects = objects;
        this.imageHandler = new ImageHandler();
        potion = imageHandler.getImage("imgs/potion.png");
        coinFront = imageHandler.getImage("imgs/coin_front.png");
        coinBack = imageHandler.getImage("imgs/coin_back.png");
        coinSide = imageHandler.getImage("imgs/coin_side.png");
        counter = 0;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (IGameObject object : objects){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(object.getPos(), 10, 10);
            if (object.getWidth() == 10) {
                if (counter < 75) {
                    g2.drawImage(coinFront, drawInformation.get(0), drawInformation.get(1), 30, 30, null);
                } else if (counter < 150) {
                    g2.drawImage(coinSide, drawInformation.get(0), drawInformation.get(1), 30, 30, null);
                } else if (counter < 225) {
                    g2.drawImage(coinBack, drawInformation.get(0), drawInformation.get(1), 30, 30, null);
                } else if (counter < 300) {
                    g2.drawImage(coinSide, drawInformation.get(0), drawInformation.get(1), 30, 30, null);
                } else {
                    counter = 0;
                }
            }else {
                g2.drawImage(potion, drawInformation.get(0), drawInformation.get(1), 30, 30, null);

            }
        }
        counter++;
    }
}
