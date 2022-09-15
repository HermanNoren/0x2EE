package view.drawers;

import sprites.ISprite;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

public class TileDrawer implements IDrawer {

    ArrayList<ISprite> tiles;

    private Camera camera;
    public TileDrawer(ArrayList<ISprite> tiles, Camera camera) {
        this.camera = camera;
        this.tiles = tiles;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (ISprite tile: tiles) {
            g2.setColor(Color.black);
            g2.fillRect((int) (tile.getPos().x - camera.getOffset().x), (int) (tile.getPos().y - camera.getOffset().y), tile.getWidth(), tile.getHeight());
        }
    }
}
