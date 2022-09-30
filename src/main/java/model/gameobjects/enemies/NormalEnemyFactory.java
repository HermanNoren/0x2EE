package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;

import java.util.List;
import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override

    public Entity createEnemy(Game game) {
        GameMap gameMap = game.getGameMap();
        List<IGameObject> possibleSpawnableLocations = gameMap.getPassableTerrains();

        int spawnableLocations = possibleSpawnableLocations.size()-1;
        Random rand = new Random();
        IGameObject randomTerrain = possibleSpawnableLocations.get(rand.nextInt(spawnableLocations));
        System.out.println(randomTerrain.getPos().x);
        NormalEnemy normalEnemy = new NormalEnemy((int) randomTerrain.getPos().x*48, (int) randomTerrain.getPos().y*48, game);
        return normalEnemy;
    }

}
