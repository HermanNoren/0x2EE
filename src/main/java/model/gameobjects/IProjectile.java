package model.gameobjects;

public interface IProjectile {
    EDirection getDirection();
    void update(double dt);
}
