package model.upgradables.weapon;

import model.gameinterfaces.IProjectileAddable;
import model.upgradables.IUpgradable;
import model.gameobjects.Projectile;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;

public class Weapon implements IUpgradable {
    private int damage;
    private int currentLevel;
    private int ammo;
    private int currentPrice;
    private boolean reloading;

    /**
     * The main weapon of the
     */
    public Weapon() {
        damage = 10;
        currentLevel = 1;
        ammo = 7;
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
    public void upgrade() {
        this.damage++;
        this.currentLevel++;

    }

    @Override
    public int statsIfUpgraded() {
        return damage + 1;
    }

    @Override
    public int upgradeCost() {
        currentPrice = currentLevel * growth;
        return currentPrice;
    }

     @Override
     public int currentStats() {
         return damage;
     }

    @Override
    public int currentPrice() {
        currentPrice = currentLevel * growth;
        return currentPrice;
    }

}
