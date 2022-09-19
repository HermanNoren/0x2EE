package mapclasses;

import config.Config;
import sprites.ISprite;

import java.util.ArrayList;

public class TerrainBorder {
    ArrayList<Tile> border;

    public TerrainBorder(int width, int height) {
        border =  createSurrounding();
    }

    /**
     * This method will create the walls of the map,
     * Nested for loops to iterate through the canvas.
     */
    private ArrayList<Tile>createSurrounding(){

        ArrayList<Tile> tiles = new ArrayList<>();

        for(int i = 0; i < Config.SCREEN_HEIGHT; i++){
            tiles.add(new Tile(0, i, true));
            tiles.add(new Tile(0 + Config.SCREEN_WIDTH, i , true));
        }
        for(int i = 0; i < Config.SCREEN_WIDTH; i++){
            tiles.add(new Tile(i, 0, true));
            tiles.add(new Tile(i, Config.SCREEN_HEIGHT, true));
        }
        return tiles;
    }

    public ArrayList<ISprite> getTerrainBorder() {
        return new ArrayList<>(border);
    }

}

