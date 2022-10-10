package model.gameobjects;


import config.Config;
import controllers.EDirection;
import model.helperclasses.Vector2;


public class Projectile implements IGameObject {


    private final int size = Config.SPRITE_SIZE / 2;
    private final Vector2 pos;
    private final Vector2 vel;

    public Projectile(Vector2 pos, EDirection direction) {
        this.pos = pos;
        vel = new Vector2(0, 0);
        switch (direction) {
            case RIGHT -> vel.setX(10);
            case LEFT -> vel.setX(-10);
            case UP -> vel.setY(-10);
            case DOWN -> vel.setY(10);
        }

    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * @return the coordinates for the center of the projectile as a vector.
     */
    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    /**
     * @return false - projectiles collides with entities.
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Updates the position of the projectile
     * @param dt passed time since last update
     */
    @Override
    public void update(double dt) {
        pos.setX(pos.getX() + vel.getX() * dt);
        pos.setY(pos.getY() + vel.getY() * dt);
    }
}
