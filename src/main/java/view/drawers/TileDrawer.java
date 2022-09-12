package view.drawers;

import sprites.ISprite;

import java.awt.*;
import java.util.ArrayList;

public class TileDrawer implements IDrawer {

    ArrayList<ISprite> tiles;
    public TileDrawer(ArrayList<ISprite> tiles) {
        this.tiles = tiles;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (ISprite tile: tiles) {
            g2.setColor(Color.black);
            g2.fillRect((int) tile.getPos().x, (int) tile.getPos().y, tile.getWidth(), tile.getHeight());
        }
    }
}
