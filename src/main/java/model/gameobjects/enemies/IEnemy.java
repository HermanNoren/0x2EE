package model.gameobjects.enemies;

public interface IEnemy{
    int getHealth();
    int getSize();
    void specialAbility();
    void update(double dt);
}
