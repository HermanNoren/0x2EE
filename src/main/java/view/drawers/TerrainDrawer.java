package view.drawers;

import sprites.ISprite;

import java.awt.*;
import java.util.ArrayList;

public class TerrainDrawer implements IDrawer {

    ArrayList<ISprite> terrain;
    public TerrainDrawer(ArrayList<ISprite> terrain) {
        this.terrain = terrain;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (ISprite terrain: terrain) {
            g2.setColor(Color.black);
            g2.fillRect((int) terrain.getPos().x, (int) terrain.getPos().y, terrain.getWidth(), terrain.getHeight());
        }
    }
}

