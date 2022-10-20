package model.upgradables.weapon;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;
import model.gameobjects.EDirection;
import model.Vector2;
import model.upgradables.Upgradable;
/**
 * The weapon class which delegated to the player (has-a relation) when the
 * player is constructed.
 * @Author Gustav Gille
 */
public class Weapon extends Upgradable implements IWeapon {

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
