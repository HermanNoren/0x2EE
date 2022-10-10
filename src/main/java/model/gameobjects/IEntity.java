package model.gameobjects;

import controllers.EDirection;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;

public interface IEntity {
    Vector2 getVel();
    Vector2 getPos();
    Vector2 getAcc();
    void setVel(Vector2 vel);
    void setAcc(Vector2 acc);
    void setPos(Vector2 pos);
    double getVelX();
    void setVelX(double velX);
    void setPosX(double posX);
    double getPosX();
    void setAccX(double accX);
    double getAccX();
    double getVelY();
    void setVelY(double velY);
    void setPosY(double posY);
    double getPosY();
    void setAccY(double accY);
    double getAccY();
    Terrain getMapLocation(Terrain[][] coordinates);
    EDirection getDirection();
    EDirection getLastDirection();
    void setDirection(EDirection direction);
    void setHealth(int health);
    void setMaxHp(int maxHp);
    int getHealth();
    double getMaxHp();
    void damageTaken(int damage);

}
