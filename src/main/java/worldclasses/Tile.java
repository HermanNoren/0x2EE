package worldclasses;

import helperclasses.Rect;
import helperclasses.Vector2;

public class Tile {
    private Vector2 pos;
    private Rect rect;

    public Tile(int x, int y) {
        pos = new Vector2(x, y);
        rect = new Rect(x, y, 16, 16);
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }
}