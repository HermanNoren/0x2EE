package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.IGameObject;
import model.mapclasses.GameMap;

import java.util.List;
import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Game game, Random rand) {
        GameMap gameMap = game.getGameMap();
        List<IGameObject> possibleSpawnableLocations = gameMap.getPassableTerrains();
        int spawnableLocations = possibleSpawnableLocations.size();
        IGameObject randomTerrain = possibleSpawnableLocations.get(rand.nextInt(spawnableLocations));
        NormalEnemy normalEnemy = new NormalEnemy((int) randomTerrain.getPos().getX(), (int) randomTerrain.getPos().getY(), game);
        return normalEnemy;
    }
}
