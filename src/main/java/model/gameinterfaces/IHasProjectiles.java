package model.gameinterfaces;

import model.gameobjects.Projectile;

import java.util.List;

public interface IHasProjectiles {
    List<Projectile> getProjectiles();
    void makePlayerShoot();
    void addProjectile(Projectile p);
}
