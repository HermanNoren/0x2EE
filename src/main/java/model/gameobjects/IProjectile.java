package model.gameobjects;

/**
 * Return the direction of the Projectile and update it.
 */
public interface IProjectile {
    EDirection getDirection();
    void update(double dt);
}
