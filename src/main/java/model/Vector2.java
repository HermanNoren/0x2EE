package model;

/**
 * Object used through delegation to act as the delegates´ position object. Used
 * in multiple places such as calculating enemy movement.
 * @author Herman Norén
 */
public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

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

}
