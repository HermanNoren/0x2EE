package helperclasses;

public class Rect {
    private int width;
    private int height;
    private int x;
    private int y;

    public Rect(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // TODO
    public boolean intersects(Rect other) {

        return false;
    }
}
