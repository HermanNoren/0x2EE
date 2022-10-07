package view.drawers;

import model.gameobjects.Shop;
import config.Config;
import java.awt.*;
import java.util.List;

/**
 * Drawer that represents a pop-up text to communicate to the player that the shop is can be interacted with.
 */
public class InteractShopTextDrawer implements IDrawer{
    public int passableValue;
    private final Shop shop;
    String popUpText = "Press Enter to enter!";

    public InteractShopTextDrawer(Shop shop){
        this.shop = shop;
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(Config.buttonFont);
        if(shop.playerOnShop){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shop.getPos(), passableValue, passableValue);
            g2.drawString(popUpText, drawInformation.get(0)  - (g2.getFontMetrics().stringWidth(popUpText)/2) + shop.getWidth()/2, drawInformation.get(1) -10);
        }
    }
}
