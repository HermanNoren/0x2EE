package Model.helperclasses;

public class Rect {
    private int width;
    private int height;
    private Vector2 pos;

    public Rect(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        pos = new Vector2(x, y);
    }

    public Rect(Rect rect) {
        this.width = rect.getWidth();
        this.height = rect.getHeight();
        pos = new Vector2(rect.getPos());
    }

    public int getWidth() {
        return width;
    }
    public double getPosX(){
        return pos.getX();
    }
    public double getPosY(){
        return pos.getY();
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    // TODO
    public boolean intersects(Rect other) {

        return false;
    }

    /**
     * Logic to determine if the objects intersect,
     * compare their coordinates and return true
     * if they intersect eachother.
     * Remember to add the width and height to said
     * objects when calculating if the do intersect.
     * @param position
     * @param position1
     * @return
     */
    public boolean intersects(Vector2 position, Vector2 position1) {

        return true;
    }
}
