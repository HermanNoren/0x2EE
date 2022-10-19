package model.gameobjects;

/**
 * Used to abstract so that only the getter and setter for money is reachable.
 */
public interface IHasUpgradable extends IHasArmor, IHasWeapon {
    int getMoney();
    void setMoney(int value);
}
