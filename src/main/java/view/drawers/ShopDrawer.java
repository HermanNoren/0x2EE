package view.drawers;


import model.gameobjects.Shop;
import model.helperclasses.DrawerHelper;
import model.helperclasses.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ShopDrawer implements IDrawer{
    private BufferedImage frame1, lights1;

    private final Shop shop;

    private final ImageHandler imageHandler;
    public ShopDrawer(Shop shop){
        this.shop = shop;
        this.imageHandler = new ImageHandler();
        initShopImages();
    }



    private void initShopImages(){
        try{
           frame1 = imageHandler.getImage("imgs/shopmovement/Shoppiskel2 copy.png"); //scale twice as large
            lights1 = imageHandler.getImage("imgs/shopmovement/flashingShop/shopLights1.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shop.getPos(), shop.getWidth(), shop.getHeight());
        if(shop.isInteractable){
            g2.drawImage(lights1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }else{
            g2.drawImage(frame1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }

}
}
