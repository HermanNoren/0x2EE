package model.gameobjects.enemies;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;

import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, GameMap gameMap) {
        Vector2 location = EnemySpawner.chooseRandomLocation(gameMap);
        NormalEnemy normalEnemy = new NormalEnemy((int) location.getX(), (int) location.getY(), gameMap.getGameMapCoordinates(), targetEntity);
        return normalEnemy;
    }
}
