package view.drawers;

import java.awt.*;
/**
 * Drawer which gives access to the ability to draw on the window.
 */
public interface IDrawer {
    /**
     * Called when to draw.
     * @param g2, see Java Graphics2D documentation.
     */
    void draw(Graphics2D g2);
}
