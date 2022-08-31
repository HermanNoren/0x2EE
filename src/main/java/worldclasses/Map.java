package worldclasses;

import config.config;
import sprites.Sprite;

import java.util.ArrayList;

public class Map {

    private String[] map = new String[] {
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                  WWWW                  ",
            "                     W                  ",
            "                     W                  ",
            "                     W                  ",
            "                      W                  ",
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

    ArrayList<Sprite> tiles;

    public Map() {
        tiles = createMap();
    }

    public ArrayList<Sprite> getTiles() {
        return new ArrayList<>(tiles);
    }

    private ArrayList<Sprite> createMap() {
        ArrayList<Sprite> tiles = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String row : map) {
            for (int i = 0; i < row.length(); i++) {
                char tile = row.charAt(i);

                switch(tile) {
                    case 'W':
                        tiles.add(new Tile(x, y));
                }
                x += config.SPRITE_SIZE;
            }
            x = 0;
            y += config.SPRITE_SIZE;
        }
        return tiles;
    }
}

