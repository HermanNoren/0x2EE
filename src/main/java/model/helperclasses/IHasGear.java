package model.helperclasses;

import model.gameobjects.IHasArmor;
import model.gameobjects.IHasWeapon;

public interface IHasGear extends IHasArmor, IHasWeapon {
    int getMoney();
    void setMoney(int value);
}
