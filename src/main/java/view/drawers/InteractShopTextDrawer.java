package view.drawers;

import model.gameinterfaces.IHasShop;
import model.gameobjects.Shop;
import config.Config;
import model.Vector2;

import java.awt.*;
import java.util.List;

/**
 * Drawer that represents a pop-up text to communicate to the player that the shop is
 * close enough to be interacted with.
 * @author Gustav Gille
 */
public class InteractShopTextDrawer implements IDrawer{
    /**
     * A throw away int which is never used. Static to be as fast as possible. Would need
     * Work around in DrawerHelper which seems unnecessary for this project, although it
     * would be good in a larger OOP project.
     */
    private static int passableValue;
    private final Vector2 shopPosition;
    private final int shopWidth;
    private final IHasShop shop;
    /**
     * String used to determine the text above the shop.
     */
    private String popUpText = "Enter to shop";

    public InteractShopTextDrawer(IHasShop shop){
        this.shop = shop;
        this.shopPosition = shop.getShop().getPos();
        this.shopWidth = shop.getShop().getWidth();
    }

    /**
     * Calculations done to center the text,g2.FontMetrics().StringWidth() is
     * used to center the length of the string above the store. The height is
     * added to put the text above instead of on the shop.
     * @param g2 The drawing interface
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(Config.BUTTON_FONT);
        if(shop.isPlayerOnShop()){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shopPosition, passableValue, passableValue);
            int heightOffset = -10;
            g2.drawString(popUpText, drawInformation.get(0) - (g2.getFontMetrics().stringWidth(popUpText)/2) + shopWidth/2, drawInformation.get(1) + heightOffset);
        }
    }
}
