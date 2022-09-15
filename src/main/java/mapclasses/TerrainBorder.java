package mapclasses;

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

        for(int i = 0; i < 1000; i++){
            tiles.add(new Tile(0, i));
            tiles.add(new Tile(i,0));
        }
        return tiles;
    }
    public Tile addNewTile(int x, int y){
        return new Tile(x, y);
    }

    public ArrayList<ISprite> getTerrainBorder() {
        return new ArrayList<>(border);
    }


}

