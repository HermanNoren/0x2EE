package view.drawers;


import model.gameobjects.Player;
import model.gameobjects.Shop;
import model.helperclasses.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopDrawer implements IDrawer{
    private BufferedImage frame1, lights1;

    private Shop shopObject;

    private ImageHandler imageHandler;
    private Player player;
    public ShopDrawer(Shop shopObject, Player player){
        this.player = player;
        this.shopObject = shopObject;
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
        List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shopObject.getPos(), shopObject.getWidth(), shopObject.getHeight());
        if(player.isInteractable){
            g2.drawImage(lights1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);

        }else{
            g2.drawImage(frame1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }

}
}
