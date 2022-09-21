package view.drawers;

import model.Game;
import model.mapclasses.Terrain;
import model.gameobjects.IGameObject;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TerrainDrawer implements IDrawer {

    private ArrayList<IGameObject> tiles;
    private Camera camera;
    private Game game = Game.getInstance();


    private ArrayList<Terrain> path;
    private HashMap<String, Terrain> grass;

    public TerrainDrawer(ArrayList<IGameObject> tiles, HashMap<String, Terrain> grass, Camera camera) {
        this.camera = camera;
        this.tiles = tiles;
        this.grass = grass;

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        grass.forEach((key, tile) -> g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight()));

        g2.setColor(Color.BLUE); // Draws out the path, temporary just for testing.
        path = game.getPath();
        path.forEach((tile) -> g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight()));

        for (IGameObject tile: tiles) {
            g2.setColor(Color.black);
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(tile.getPos(), tile.getWidth(), tile.getHeight(), camera);
            g2.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
            g2.fillRect((int) tile.getPos().getX(), (int) tile.getPos().getY(), tile.getWidth(), tile.getHeight());
        }

        for (IGameObject terrain: tiles) {
            g2.setColor(Color.black);
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(terrain.getPos(), terrain.getWidth(), terrain.getHeight(), camera);
            g2.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}

