package view.drawers;

import model.helperclasses.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InShopDrawer implements IDrawer{

    private int money;
    private final ImageHandler imageHandler;
    BufferedImage weaponActive, weaponInactive;

    public InShopDrawer(int money) {
        this.imageHandler = new ImageHandler();
        this.money = money;

    }


    private void initShopImages(){
        try{
            weaponActive = imageHandler.getImage("imgs/shop/weapon/weaponActive.png");
            weaponInactive = imageHandler.getImage("imgs/shop/ShoppingPNGS/shoptopright.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }
    @Override
    public void draw(Graphics2D g2) {

    }
}
