package gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.ItemSpawner.Spawner;
import model.mapclasses.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpawnerTest {

    Game game = new Game();
    Spawner spawner = new Spawner(game.getGameMap().getPassableTiles(),game);

    @Test
    void test_if_spawn_method_is_called_many_times_an_item_should_have_spawned(){
        for (int i = 0; i < 100; i++){
            spawner.spawnItem();
        }
        assertTrue(spawner.getSpawnedItems().size() > 0);
    }

    @Test
    void test_if_an_item_is_cleared_then_the_amount_of_spawned_items_should_decrease(){
        for (int i = 0; i < 100; i++){
            spawner.spawnItem();
        }
        int amount = spawner.getSpawnedItems().size();
        IItem item = spawner.getSpawnedItems().get(0);
        spawner.clearItem(item);
        assertTrue(spawner.getSpawnedItems().size() < amount);
    }

    @Test
    void test_that_an_item_spawned_on_average_position_is_reachable(){
        game.spawnEnemy(1);
        game.spawnEnemy(1);
        spawner.spawnItem();
        for (int i = 0; i < 100; i++){
            spawner.spawnItem();
        }
        IItem item = spawner.getSpawnedItems().get(0);
        boolean passable = false;
        for (Tile tile : game.getGameMap().getPassableTiles()){
            if (tile.getPos().getX() == item.getPos().getX() && tile.getPos().getY() == item.getPos().getY()) {
                passable = true;
                break;
            }
        }
        assertTrue(passable);
    }


}
