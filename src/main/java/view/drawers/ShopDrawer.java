package view.drawers;


import model.gameobjects.theShop.Shop;
import model.helperclasses.ImageHandler;
import view.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopDrawer implements IDrawer{
    Camera camera;
    private BufferedImage frame1;

    private Shop shopObject;

    private ImageHandler imageHandler;

    public ShopDrawer(Shop shopObject){
        this.shopObject = shopObject;
        this.camera = camera;
        this.imageHandler = new ImageHandler();
        initShopImages();
    }

    private void initShopImages(){
        try{
             frame1 = imageHandler.getImage("imgs/shopmovement/Shoppiskel2 copy.png"); //scale twice as large
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shopObject.getPos(), shopObject.getWidth(), shopObject.getHeight());
        g2.drawImage(frame1, drawInformation.get(0),drawInformation.get(1),80,80, null);
    }

}
