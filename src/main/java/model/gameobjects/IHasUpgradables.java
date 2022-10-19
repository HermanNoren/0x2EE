package model.gameobjects;

public interface IHasUpgradables extends IHasArmor, IHasWeapon {
    int getMoney();
    void setMoney(int value);
}
