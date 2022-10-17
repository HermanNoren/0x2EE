package model.gameinterfaces;

import model.gameobjects.enemies.Enemy;

import java.util.List;

public interface IHasEnemies {
    List<Enemy> getEnemies();
}
