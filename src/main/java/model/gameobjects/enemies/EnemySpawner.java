package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.List;
import java.util.Random;

public class EnemySpawner {
    public EnemySpawner(){
    }
    public static Vector2 chooseRandomLocation(GameMap gameMap){
        List<Tile> spawnableLocations = gameMap.getPassableTiles();
        int nrPossibleSpawnLocations = spawnableLocations.size();
        Random random = new Random();
        Tile randomSpawnableTile = spawnableLocations.get(random.nextInt(nrPossibleSpawnLocations-1));
        double posX = randomSpawnableTile.getPos().getX();
        double posY = randomSpawnableTile.getPos().getY();
        return new Vector2(posX, posY);
    }
}
