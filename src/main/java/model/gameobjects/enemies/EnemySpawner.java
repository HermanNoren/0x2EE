package model.gameobjects.enemies;

import model.mapclasses.GameMap;
import model.mapclasses.Terrain;

import java.util.List;

public class EnemySpawner implements IEnemySpawner {
    private final GameMap gameMap;
    private EnemyFactory enemyFactory;
    private List<Terrain> spawnableLocations;

    public EnemySpawner(EnemyFactory enemyFactory, GameMap gameMap){
        this.enemyFactory = enemyFactory;
        this.gameMap = gameMap;
        this.spawnableLocations = gameMap.getPassableTerrains();
    }


    @Override
    public void spawnEnemy(IEnemy enemy) {

    }
}
