package model.gameobjects;


import config.Config;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;


public class Projectile implements IGameObject, IProjectile {
    private final int size = Config.SPRITE_SIZE / 2;
    private final Vector2 pos;
    private final Vector2 vel;
    private EDirection direction;
    public Projectile(Vector2 pos, EDirection direction) {
        this.pos = pos;
        vel = new Vector2(0, 0);
        switch (direction) {
            case right -> vel.setX(10);
            case left -> vel.setX(-10);
            case up -> vel.setY(-10);
            case down -> vel.setY(10);
        }
        this.direction = direction;
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

    @Override
    public EDirection getDirection() {
        return direction;
    }

    @Override
    public void update(double dt) {
        pos.setX(pos.getX() + vel.getX() * dt);
        pos.setY(pos.getY() + vel.getY() * dt);
    }
}
