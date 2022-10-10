package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.mapclasses.GameMap;

import java.util.List;
import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(GameMap gameMap, Entity targetEntity, Random rand) {
        List<IGameObject> possibleSpawnableLocations = gameMap.getPassableTerrains();
        int spawnableLocations = possibleSpawnableLocations.size();
        IGameObject randomTerrain = possibleSpawnableLocations.get(rand.nextInt(spawnableLocations));
        NormalEnemy normalEnemy = new NormalEnemy((int) randomTerrain.getPos().getX(), (int) randomTerrain.getPos().getY(), gameMap.getGameMapCoordinates(),targetEntity);
        return normalEnemy;
    }
}
