package view.drawers;

import sprites.ISprite;

import java.awt.*;
import java.util.ArrayList;

public class TileDrawer implements IDrawer {

    ArrayList<ISprite> tiles;
    ArrayList<ISprite> grass;
    public TileDrawer(ArrayList<ISprite> tiles, ArrayList<ISprite> grass) {
        this.tiles = tiles;
        this.grass = grass;
    }

    @Override
    public void draw(Graphics2D g2) {

        for (ISprite tile: grass) {
            g2.setColor(Color.green);
            g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight());
        }
        for (ISprite tile: tiles) {
            g2.setColor(Color.black);
            g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight());
        }



    }
}
