package view.drawers;

import helperclasses.Vector2;
import mapclasses.Terrain;
import sprites.ISprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TileDrawer implements IDrawer {

    ArrayList<ISprite> tiles;
    HashMap<String, Terrain> grass;
    public TileDrawer(ArrayList<ISprite> tiles, HashMap<String, Terrain> grass) {
        this.tiles = tiles;
        this.grass = grass;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        grass.forEach((key, tile) -> g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight()));

        for (ISprite tile: tiles) {
            g2.setColor(Color.black);
            g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight());
        }



    }
}
