package gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.ItemSpawner.Spawner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpawnerTest {

    Game game = new Game();
    Spawner spawner = new Spawner(game.getGameMap().getPassableTiles(),game);

    @Test
    void test_if_an_item_is_spawned_then_the_amount_of_spawned_items_should_increase(){
        spawner.spawnItem();
        assertTrue(spawner.getSpawnedItems().size() > 0);
    }

    @Test
    void test_if_an_item_is_cleared_then_the_amount_of_spawned_items_should_decrease(){
        spawner.spawnItem();
        int amount = spawner.getSpawnedItems().size();
        IItem item = spawner.getSpawnedItems().get(0);
        spawner.clearItem(item);
        assertTrue(spawner.getSpawnedItems().size() < amount);
    }


}
