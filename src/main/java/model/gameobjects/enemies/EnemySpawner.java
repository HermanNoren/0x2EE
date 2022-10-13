package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;

import java.util.List;
import java.util.Random;

public class EnemySpawner implements IEnemySpawner {
    private final GameMap gameMap;
    private EnemyFactory enemyFactory;
    private List<Terrain> spawnableLocations;

    public EnemySpawner(EnemyFactory enemyFactory, GameMap gameMap){
        this.enemyFactory = enemyFactory;
        this.gameMap = gameMap;
        this.spawnableLocations = gameMap.getPassableTerrains();
    }

    private void chooseLocation(IEnemy enemy, int radius){
        Entity target = enemy.getTargetEntity();
        Terrain targetLocation = target.getMapLocation();

        double targetPosX = targetLocation.getPos().getX();
        double targetPosY = targetLocation.getPos().getY();

        int possibleSpawnLocations = spawnableLocations.size();

        double spawnPosX = targetPosX + radius;
        double spawnPosY = targetPosY + radius;


    }

    public Vector2 chooseRandomLocation(){
        int nrPossibleSpawnLocations = spawnableLocations.size();
        Random random = new Random();
        Terrain randomSpawnableTerrain = spawnableLocations.get(random.nextInt(nrPossibleSpawnLocations-1));
        double posX = randomSpawnableTerrain.getPos().getX();
        double posY = randomSpawnableTerrain.getPos().getY();
        return new Vector2(posX, posY);
    }


    @Override
    public void spawnEnemy(IEnemy enemy, int radius) {

    }
}
