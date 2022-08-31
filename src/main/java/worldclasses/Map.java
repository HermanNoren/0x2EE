package worldclasses;

import helperclasses.Rect;
import helperclasses.Vector2;

import java.util.ArrayList;

public class Map {

    private String[] map = new String[] {
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                  WWWW                  ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
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

    ArrayList<Tile> tiles;

    public Map() {
        tiles = createMap();
    }

    public ArrayList<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }

    private ArrayList<Tile> createMap() {
        ArrayList<Tile> tiles = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String row : map) {
            for (int i = 0; i < row.length(); i++) {
                char tile = row.charAt(i);

                switch(tile) {
                    case 'W':
                        tiles.add(new Tile(x, y));
                }
                x += 16;
            }
            x = 0;
            y += 16;
        }
        return tiles;
    }
}

