package model.gameobjects;

import model.armor.Armor;
import model.weapons.Weapon;

import java.util.List;
import model.gameinterfaces.IProjectileAddable;

public interface IPlayer {

    void shoot(IProjectileAddable addable);
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
