package model.gameobjects.enemies;
import model.Vector2;
import model.mapclasses.Tile;
import java.util.List;
import java.util.Random;
/**
 * Used for setting a random position for an enemy.
 * @author Arthur Alexandersson
 */
public class EnemySpawner{
    private final List<Tile> spawnableLocations;

    public EnemySpawner(List<Tile> passableTiles){
        this.spawnableLocations = passableTiles;
    }

    public Vector2 chooseRandomLocation(){
        int nrPossibleSpawnLocations = spawnableLocations.size();
        Random random = new Random();
        Tile randomSpawnableTile = spawnableLocations.get(random.nextInt(nrPossibleSpawnLocations-1));
        double posX = randomSpawnableTile.getPos().getX();
        double posY = randomSpawnableTile.getPos().getY();
        return new Vector2(posX, posY);
    }

}
