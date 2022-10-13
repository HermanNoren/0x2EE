package model.gameobjects.enemies;

import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.List;
import java.util.Random;

public class EnemySpawner implements IEnemySpawner {
    private final GameMap gameMap;
    private EnemyFactory enemyFactory;
    private List<Tile> spawnableLocations;

    public EnemySpawner(EnemyFactory enemyFactory, GameMap gameMap){
        this.enemyFactory = enemyFactory;
        this.gameMap = gameMap;
        this.spawnableLocations = gameMap.getPassableTiles();
    }

    private void chooseLocation(IEnemy enemy, int radius){
        Entity target = enemy.getTargetEntity();
        Tile targetLocation = target.getMapLocation();

        double targetPosX = targetLocation.getPos().getX();
        double targetPosY = targetLocation.getPos().getY();

        int possibleSpawnLocations = spawnableLocations.size();

        double spawnPosX = targetPosX + radius;
        double spawnPosY = targetPosY + radius;

    }

    public Vector2 chooseRandomLocation(){
        int nrPossibleSpawnLocations = spawnableLocations.size();
        Random random = new Random();
        Tile randomSpawnableTile = spawnableLocations.get(random.nextInt(nrPossibleSpawnLocations-1));
        double posX = randomSpawnableTile.getPos().getX();
        double posY = randomSpawnableTile.getPos().getY();
        return new Vector2(posX, posY);
    }


    @Override
    public void spawnEnemy(IEnemy enemy, int radius) {

    }
}
