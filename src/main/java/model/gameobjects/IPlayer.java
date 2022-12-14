package model.gameobjects;

import model.gameinterfaces.IHasProjectiles;

/**
 * Used to abstract method from the regular Player instance, use a IPlayer instead.
 */
public interface IPlayer {
    void shoot(IHasProjectiles addable);
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
}
