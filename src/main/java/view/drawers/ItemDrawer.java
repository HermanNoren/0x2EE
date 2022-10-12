package view.drawers;

import controllers.ImageSwitcherController;
import model.gameobjects.IGameObject;
import model.gameobjects.ItemSpawner.IItem;
import model.helperclasses.ImageHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ItemDrawer implements IDrawer, IIteratedImageDrawer {

    private List<IItem> objects;

    private ImageHandler imageHandler;

    private BufferedImage potion, coinFront, coinBack, coinSide1, coinSide2;

    private int counter;

    private List<BufferedImage> imageList;
    private BufferedImage coinImage;

    private int index;

    public ItemDrawer(List<IItem> objects){
        this.objects = objects;
        this.imageHandler = new ImageHandler();
        potion = imageHandler.getImage("imgs/drops/potion.png");
        coinFront = imageHandler.getImage("imgs/drops/coin_front.png");
        coinBack = imageHandler.getImage("imgs/drops/coin_back.png");
        coinSide1 = imageHandler.getImage("imgs/drops/coin_side_1.png");
        coinSide2 = imageHandler.getImage("imgs/drops/coin_side_2.png");
        counter = 0;
        imageList = new ArrayList<>();
        imageList.add(coinFront);
        imageList.add(coinSide1);
        imageList.add(coinBack);
        imageList.add(coinSide2);
        coinImage = coinFront;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (IGameObject object : objects){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(object.getPos(), 10, 10);
            if (object.getWidth() == 10) {
                g2.drawImage(coinImage, drawInformation.get(0), drawInformation.get(1), 30, 30, null);
            }else {
                g2.drawImage(potion, drawInformation.get(0), drawInformation.get(1), 30, 30, null);

            }
        }

    }

    @Override
    public void switchImage() {
        coinImage = imageList.get(index % 4);
        index++;
    }
}
