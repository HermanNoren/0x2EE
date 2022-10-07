package model.gameobjects;

import java.util.List;

public interface IPlayer {

    void shoot(List<Projectile> projectiles);
    void stopCurrentMovement();
    void moveX(double dt);
    void moveY(double dt);
    int getScore();
    void addScore(int score);
    void addMoney(int money);
    int getMoney();
}
