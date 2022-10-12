package model.weapons;

import controllers.EDirection;
import model.gameobjects.IUpgradable;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;

import java.util.List;

public class Weapon implements IUpgradable {
    public int damage;
    private int currentLevel;
    public int ammo;
    public boolean reloading;

    private int currentWeaponPrize;


    /**
     * The main weapon of the
     */
    public Weapon(int damage, int ammo) {
        this.damage = damage;
        this.ammo = ammo;
        reloading = false;
        currentLevel = 1;
        this.currentWeaponPrize = 10;
    }



    public void shoot(Vector2 pos, EDirection direction, List<Projectile> projectiles){
        if (ammo != 0) {
            ammo--;
            projectiles.add(new Projectile(pos, direction));
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

    @Override
    public void levelUp() {
        this.currentLevel++;
        this.damage++;
    }

    @Override
    public int currentLevel() {
        return currentLevel;
    }

    @Override
    public int currentPrice() {
        return 0; //TODO
    }

    @Override
    public int statsIfUpgraded() {
        return 0; //TODO
    }

    @Override
    public int upgradeCost() {
        return 0; //TODO
    }
}
