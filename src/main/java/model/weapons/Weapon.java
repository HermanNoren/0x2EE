package model.weapons;

import controllers.EDirection;
import model.Game;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;

import java.util.List;

public class Weapon {

    public int damage;

    private int currentLevel;
    public int ammo;
    public boolean reloading;

    private final Game game;

    /**
     * The main weapon of the
     */
    public Weapon(int damage, int ammo, Game game){
        this.damage = damage;
        this.ammo = ammo;
        reloading = false;
        this.game = game;
        currentLevel = 1;

    }

    public void levelUpWeapon(){
        this.currentLevel++;
        this.damage *=1.5;
    }


    public int getLevel(){
        return currentLevel;
    }


    public void shoot(Vector2 pos, EDirection direction, List<Projectile> projectiles){
        if (ammo != 0) {
            ammo--;
            game.addProjectile(new Projectile(pos, direction));
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
