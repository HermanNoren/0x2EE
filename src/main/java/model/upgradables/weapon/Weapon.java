package model.upgradables.weapon;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;
import model.gameobjects.EDirection;
import model.helperclasses.Vector2;
import model.upgradables.Upgradable;

public class Weapon extends Upgradable implements IWeapon {
    private int ammo;
    private boolean reloading;

    /**
     * The weapon class, delegated to the player (has-a relation).
     */
    public Weapon() {
        super(1, 40);
        ammo = 7;
        reloading = false;
    }
    /**
     * Method called for creating bullets.
     */

    public void shoot(Vector2 pos, EDirection direction, IHasProjectiles addable){
        if (ammo != 0) {
            ammo--;
            addable.addProjectile(new Projectile(pos, direction));
        }
        else reload();
    }

    /**
     * method used for reloading the weapon, no current implementation.
     */
    private void reload(){
        reloading = true;
        // TODO: Add some kind of delay
        reloading = false;
        ammo = 7;
    }

    /**
     * @return true if weapon is reloading.
     */
    public boolean isReloading(){
        return reloading;
    }

}
