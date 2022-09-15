package view.drawers;

import sprites.ISprite;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

public class TerrainDrawer implements IDrawer {

    ArrayList<ISprite> terrain;
    private Camera camera;

    public TerrainDrawer(ArrayList<ISprite> terrain, Camera camera) {
        this.terrain = terrain;
        this.camera = camera;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (ISprite terrain: terrain) {
            g2.setColor(Color.black);
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(terrain.getPos(), terrain.getWidth(), terrain.getHeight(), camera);
            g2.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}

