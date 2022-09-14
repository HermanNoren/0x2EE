package mapclasses;

import config.Config;
import sprites.ISprite;

import java.util.ArrayList;

public class Terrain {
    ArrayList<ISprite> terrain;

    public Terrain() {
        terrain =  createSurrounding();
    }
    private ArrayList<ISprite>createSurrounding(){

        ArrayList<ISprite> tiles = new ArrayList<>();
        for(int i = 0; i < 400; i++){

        }
        return tiles;

    }

    public ArrayList<ISprite> getTerrain() {
        return new ArrayList<>(terrain);
    }
    /**
     * This method will create the walls of the map,
     * Nested for loops to iterate through the canvas.
     */
    /*
    private ArrayList<ISprite> createMap() {
        ArrayList<ISprite> tiles = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String row : gameMap) {
            for (char tile : row.toCharArray()) {
                switch(tile) {
                    case 'W':
                        tiles.add(new Tile(x, y));
                }
                x += Config.SPRITE_SIZE;
            }
            x = 0;
            y += Config.SPRITE_SIZE;
        }
        return tiles;
    }
    */
    /*
    private String[] gameMap = new String[] {
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                  WWWW                  ",
            "                     W                  ",
            "                     W                  ",
            "                     W                  ",
            "                      W                 ",
            "                      W                 ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "             WWWWWWWWWWWW               ",
            "                                        ",
            "                                        "
    };
    */
}

