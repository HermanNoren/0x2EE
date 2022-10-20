package model.gameobjects;


import config.Config;
import model.Vector2;

/**
 * Projectiles are used by the player to shoot at enemies. A projectile will be created at a given position
 * when created and travel in the given direction. It will travel with a velocity of 10. It has a width and height
 * of half the SPRITE_SIZE given in the config file.
 * @author Herman Noren
 */
public class Projectile implements IGameObject, IProjectile {
    private final int size = Config.SPRITE_SIZE / 2;
    private final Vector2 pos;
    private final Vector2 vel;
    private final EDirection direction;

    /**
     * Instantiates a projectile.
     * @param pos Position to create the projectile on.
     * @param direction Direction the created projectile will move towards
     */
    public Projectile(Vector2 pos, EDirection direction) {
        this.pos = pos;
        vel = new Vector2(0, 0);
        switch (direction) {
            case RIGHT -> vel.setX(10);
            case LEFT -> vel.setX(-10);
            case UP -> vel.setY(-10);
            case DOWN -> vel.setY(10);
        }
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return size;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return size;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public EDirection getDirection() {
        return direction;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void update(double dt) {
        pos.setX(pos.getX() + vel.getX() * dt);
        pos.setY(pos.getY() + vel.getY() * dt);
    }
}
