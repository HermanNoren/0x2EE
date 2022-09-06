package weapons;

public class Pistol implements Weapon{

    int damage;
    int ammo;

    public Pistol(){
        damage = 40;
        ammo = 7;
    }

    @Override
    public void shoot(){
        if (ammo != 0) ammo--;
        else reload();
    }

    @Override
    public void reload(){
        ammo = 7;
    }


}
