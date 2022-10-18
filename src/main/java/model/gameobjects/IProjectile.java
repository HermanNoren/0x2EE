package model.gameobjects;

import model.helperclasses.EDirection;

public interface IProjectile {
    EDirection getDirection();
    void update(double dt);
}
