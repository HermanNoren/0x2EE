package model.helperclasses;

public class Vector2 {
     public double x;
     public double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;

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
