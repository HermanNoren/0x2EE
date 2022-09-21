package weapons;

import controllers.EDirection;
import gameobjects.Projectile;
import helperclasses.Vector2;

import java.util.ArrayList;

public class Weapon {

    public int damage;
    public int ammo;
    public boolean reloading;

    /**
     * The main weapon of the
     */
    public Weapon(int damage, int ammo){
        this.damage = damage;
        this.ammo = ammo;
        reloading = false;
    }


    public void shoot(Vector2 pos, EDirection direction, ArrayList<Projectile> projectiles){
        if (ammo != 0) {
            ammo--;
            projectiles.add(new Projectile(pos, direction));
        }
        else reload();
    }

    public void reload(){
        reloading = true;
        // TODO: Add some kind of delay (might need threading?)
        reloading = false;
        ammo = 7;
    }

    /**
     *
     * @return true if weapon is reloading
     */
    public boolean isReloading(){
        return reloading;
    }

}
