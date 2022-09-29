package view.drawers;

import model.gameobjects.IGameObject;
import model.helperclasses.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemDrawer implements IDrawer{

    private List<IGameObject> objects;

    private ImageHandler imageHandler;

    private BufferedImage potion;

    public ItemDrawer(List<IGameObject> objects){
        this.objects = objects;
        this.imageHandler = new ImageHandler();
        potion = imageHandler.getImage("imgs/drops/potion.png");
    }

    @Override
    public void draw(Graphics2D g2) {
        for (IGameObject object : objects){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(object.getPos(), 10, 10);
            g2.drawImage(potion, drawInformation.get(0), drawInformation.get(1), 30, 30, null);


        }

    }
}
