package mapclasses;

import helperclasses.Vector2;
import config.Config;
import sprites.ISprite;

import java.util.ArrayList;

public class TerrainBorder {
    ArrayList<Terrain> border;

    public TerrainBorder(int width, int height) {
        border =  createSurrounding();
    }

    /**
     * This method will create the walls of the map,
     * Nested for loops to iterate through the canvas.
     */
    private ArrayList<Terrain>createSurrounding(){

        ArrayList<Terrain> tiles = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            tiles.add(new Terrain(new Vector2(0, i), true));
            tiles.add(new Terrain(new Vector2(i, 0), false));
        }
        for(int i = 0; i < Config.SCREEN_HEIGHT_IN_GAME; i++) {
            tiles.add(new Terrain(new Vector2(0, i), true));
            tiles.add(new Terrain(new Vector2(0 + Config.SCREEN_WIDTH_IN_GAME, i), true));
        }
        for(int i = 0; i < Config.SCREEN_WIDTH_IN_GAME; i++){
            tiles.add(new Terrain(new Vector2(i, 0), true));
            tiles.add(new Terrain(new Vector2(i, Config.SCREEN_HEIGHT_IN_GAME), true));
        }
        return tiles;
    }


    public ArrayList<ISprite> getTerrainBorder() {
        return new ArrayList<>(border);
    }
}

