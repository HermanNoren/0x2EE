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
            "                                        ",
            "             WWWWWWWWWWWW               ",
            "                                        ",
            "                                        "
    };

    public Map() {

    }

    public ArrayList<Tile> createMap() {
        
    }
}

class Tile {
    private Vector2 pos;
    private Rect rect;

    protected Tile(int x, int y) {
        pos = new Vector2(x, y);
        rect = new Rect(x, y, 16, 16);
    }
}

