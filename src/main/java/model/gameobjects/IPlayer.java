package model.gameobjects;

public interface IPlayer {

    void shoot();
    void stopCurrentMovement();
    void moveX(double dt);
    void moveY(double dt);
    int getScore();
    void addScore(int score);
    void addMoney(int money);
    int getMoney();
}
