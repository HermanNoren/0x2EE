package view.drawers;

import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class TileDrawer implements SpriteDrawer {

    ArrayList<Sprite> tiles;
    public TileDrawer(ArrayList<Sprite> tiles) {
        this.tiles = tiles;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Sprite tile: tiles) {
            g2.setColor(Color.black);
            g2.fillRect((int) tile.getPos().x, (int) tile.getPos().y, tile.getWidth(), tile.getHeight());
        }
    }
}
