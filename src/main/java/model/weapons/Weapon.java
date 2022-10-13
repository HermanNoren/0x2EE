package model.weapons;

import controllers.EDirection;
import model.gameobjects.IProjectileAddable;
import model.gameobjects.IUpgradable;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;

import java.util.List;

 public class Weapon implements IUpgradable {
    private int damage = 10;
    private int currentLevel = 1;
    private int ammo = 7;
    private boolean reloading;
    private int growth = 40;

    /**
     * The main weapon of the
     */
    public Weapon() {
        reloading = false;
    }

    public void shoot(Vector2 pos, EDirection direction, IProjectileAddable addable){
        if (ammo != 0) {
            ammo--;
            addable.addProjectile(new Projectile(pos, direction));
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
     * @return true if weapon is reloading
     */
    public boolean isReloading(){
        return reloading;
    }

    @Override
    public void levelUp() {
        this.damage++;
        this.currentLevel++;

    }

    @Override
    public int statsIfUpgraded() {
        return damage + 1;
    }

    @Override
    public int upgradeCost() {
        return (currentLevel * growth);
    }

     @Override
     public int currentStat() {
         return damage;
     }
 }
