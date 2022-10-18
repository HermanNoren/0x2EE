package model.upgradables.weapon;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;
import model.upgradables.Upgradable;

public class Weapon extends Upgradable implements IWeapon {
    private int ammo;
    private boolean reloading;

    /**
     * The main weapon of the
     */
    public Weapon() {
        super(1, 40);
        ammo = 7;
        reloading = false;
    }

    public void shoot(Vector2 pos, EDirection direction, IHasProjectiles addable){
        if (ammo != 0) {
            ammo--;
            addable.addProjectile(new Projectile(pos, direction));
        }
        else reload();
    }

    private void reload(){
        reloading = true;
        // TODO: Add some kind of delay
        reloading = false;
        ammo = 7;
    }

    /**
     * @return true if weapon is reloading
     */
    public boolean isReloading(){
        return reloading;
    }

}
