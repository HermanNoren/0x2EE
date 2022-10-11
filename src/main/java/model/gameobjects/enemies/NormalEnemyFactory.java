package model.gameobjects.enemies;
import model.gameobjects.Entity;
import model.mapclasses.GameMap;

import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, GameMap gameMap, Random rand) {
        NormalEnemy normalEnemy = new NormalEnemy(48, 48, gameMap.getGameMapCoordinates(), targetEntity);
        return normalEnemy;
    }
}
