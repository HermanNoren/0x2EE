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
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(tile.getPos(), tile.getWidth(), tile.getHeight(), camera);
            g2.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}
