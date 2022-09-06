package weapons;

public class Pistol implements Weapon{

    private int damage;
    private int ammo;
    private boolean reloading;

    public Pistol(){
        damage = 40;
        ammo = 7;
        reloading = false;
    }

    @Override
    public void shoot(){
        if (ammo != 0) ammo--;
        else reload();
    }

    @Override
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
    public boolean isReloading(){return reloading;}


}
