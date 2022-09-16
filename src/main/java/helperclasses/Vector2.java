package helperclasses;

public class Vector2 {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        System.out.println("new x set:" + this.getX());

    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;

    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
}
