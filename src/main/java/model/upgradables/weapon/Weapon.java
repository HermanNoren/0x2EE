package model.upgradables.weapon;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;
import model.gameobjects.EDirection;
import model.Vector2;
import model.upgradables.Upgradable;

public class Weapon extends Upgradable implements IWeapon {
    /**
     * The weapon class, delegated to the player (has-a relation).
     */
    public Weapon() {
        super(1, 2);
    }

    /**
     * Method called for creating bullets.
     */
    public void shoot(Vector2 pos, EDirection direction, IHasProjectiles addable) {
            addable.addProjectile(new Projectile(pos, direction));
    }
}
