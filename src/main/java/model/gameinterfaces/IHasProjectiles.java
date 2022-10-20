package model.gameinterfaces;

import model.gameobjects.Projectile;

import java.util.List;

/**
 * Interface a model with containing dynamic projectiles should implement
 */

public interface IHasProjectiles {
    List<Projectile> getProjectiles();
    void makePlayerShoot();
    void addProjectile(Projectile p);
}
