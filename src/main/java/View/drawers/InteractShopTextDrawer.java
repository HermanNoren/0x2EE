package View.drawers;

import Model.gameobjects.Shop;
import Model.helperclasses.DrawerHelper;
import config.Config;
import java.awt.*;
import java.util.List;

/**
 * Drawer that represents a pop-up text to communicate to the player that the shop is can be interacted with.
 */
public class InteractShopTextDrawer implements IDrawer{
    public int passableValue;
    private final Shop shop;

    public InteractShopTextDrawer(Shop shop){
        this.shop = shop;
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(Config.buttonFont);
        if(shop.playerOnShop){
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shop.getPos(), passableValue, passableValue);
            String popUpText = "Enter to Shop!";
            final int x_offset = -30;
            final int y_offset = -10;
            g2.drawString(popUpText, drawInformation.get(0) + x_offset, drawInformation.get(1) + y_offset);
        }
    }
}
