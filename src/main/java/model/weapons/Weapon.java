package model.weapons;

import controllers.EDirection;
import model.Game;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;

import java.util.List;

public class Weapon {

    public int damage;
    public int ammo;
    public boolean reloading;

    private Game game;

    /**
     * The main weapon of the
     */
    public Weapon(int damage, int ammo, Game game){
        this.damage = damage;
        this.ammo = ammo;
        reloading = false;
        this.game = game;

    }


    public void shoot(Vector2 pos, EDirection direction){
        if (ammo != 0) {
            ammo--;
            game.addProjectile(new Projectile(pos, direction));
        }
        else reload();
    }

    public void reload(){
        reloading = true;
        // TODO: Add some kind of delay
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
