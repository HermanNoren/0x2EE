package view.drawers;


import model.gameobjects.Player;
import model.gameobjects.theShop.Shop;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopDrawer implements IDrawer{
    private BufferedImage frame1, lights1, lights2, currentFrame;

    private Shop shopObject;

    private Player player;
    public ShopDrawer(Shop shopObject, Player player){
        this.player = player;
        this.shopObject = shopObject;
        initShopImages();
    }



    private void initShopImages(){
        try{
            frame1 = setImage("imgs/shopmovement/Shoppiskel2 copy.png"); //scale twice as large
            lights1 = setImage("imgs/shopmovement/flashingShop/shopLights1.png");
            lights2 = setImage("imgs/shopmovement/flashingShop/shopLights2.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }

    private BufferedImage setImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public void draw(Graphics2D g2) {
        ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shopObject.getPos(), shopObject.getWidth(), shopObject.getHeight());
        if(player.isInteractable){
            g2.drawImage(lights1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);

        }else{
            g2.drawImage(frame1, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }

}
}
