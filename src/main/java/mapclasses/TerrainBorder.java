package mapclasses;

import helperclasses.Vector2;
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

        for(int i = 0; i < 1000; i++){
            tiles.add(new Terrain(new Vector2(0,i), false));
            tiles.add(new Terrain(new Vector2(i, 0), false));
        }
        return tiles;
    }
    public Terrain addNewTile(int x, int y){
        return new Terrain(new Vector2(x, y), false);
    }

    public ArrayList<ISprite> getTerrainBorder() {
        return new ArrayList<>(border);
    }


}

