package model.gameobjects;

import model.gameinterfaces.IProjectilesGettable;
import model.upgradables.armor.Armor;
import model.upgradables.weapon.Weapon;


public interface IPlayer {

    void shoot(IProjectilesGettable addable);
    void stopCurrentXMovement();
    void stopCurrentYMovement();
    void stopAllCurrentMovement();
    void moveX(double dt);
    void moveY(double dt);
    int getScore();
    void addScore(int score);
    void addMoney(int money);
    int getMoney();
    void setMoney(int money);
    Weapon getWeapon();
    Armor getArmor();
}
