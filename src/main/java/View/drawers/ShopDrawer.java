package View.drawers;


import Model.gameobjects.Shop;
import Model.helperclasses.DrawerHelper;
import Model.helperclasses.ImageHandler;
import Model.helperclasses.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopDrawer implements IDrawer{
    private static BufferedImage topLeft, topRight, bottomLeft, bottomRight;
    private final Shop shop;


    private final ImageHandler imageHandler;
    public ShopDrawer(Shop shop){
        this.shop = shop;
        this.imageHandler = new ImageHandler();
        initShopImages();
    }


    /**
     * Initiate the 4 pictures of the shop
     */
    private void initShopImages(){
        try{
           topLeft = imageHandler.getImage("imgs/shop/ShoppingPNGS/shoptopleft.png");
           topRight = imageHandler.getImage("imgs/shop/ShoppingPNGS/shoptopright.png");
           bottomLeft = imageHandler.getImage("imgs/shop/ShoppingPNGS/shopbottomleft.png");
           bottomRight = imageHandler.getImage("imgs/shop/ShoppingPNGS/shopbottomright.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }


    /**
     * To make the view depend on the model and not the model on the view, the view has been adapted to fit the model.
     * This implements some calculating in the view as to correct the offset.
     * @param image
     * @return
     */
    public Vector2 pictureOffsetCorrector(BufferedImage image){
        if (topLeft.equals(image)) {
            return new Vector2(shop.getPos());
        }
        else if (topRight.equals(image)) {
            return new Vector2(shop.getPos().getX() + shop.getWidth()/2, shop.getPos().getY());
        }
        else if (bottomRight.equals(image)){
            return new Vector2(shop.getPos().getX() + shop.getWidth()/2, shop.getPos().getY() +  shop.getHeight()/2);
        }
        else if (bottomLeft.equals(image)){
            return new Vector2(shop.getPos().getX(), shop.getPos().getY()  + shop.getHeight()/2);
        }
        return null;
    }

    // create an offset function which aligns the PNGs correctly, must be done more dynamically than hardcoded.
    @Override
    public void draw(Graphics2D g2) {
        List<BufferedImage> pictures = new ArrayList<>(Arrays.asList(topLeft, topRight, bottomLeft, bottomRight));
        for(BufferedImage image : pictures) {
            List<Integer> drawInformationTopLeft = DrawerHelper.calculateDrawingInformation(pictureOffsetCorrector(image), shop.getWidth()/2, shop.getHeight()/2);
            g2.drawImage(image, drawInformationTopLeft.get(0), drawInformationTopLeft.get(1), drawInformationTopLeft.get(2), drawInformationTopLeft.get(3), null);
        }
    }
}
