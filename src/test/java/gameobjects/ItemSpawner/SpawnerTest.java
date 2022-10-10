package gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.IGameObject;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.ItemSpawner.Potion;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpawnerTest {

    Game game = new Game();
    Spawner spawner = new Spawner(game);

    Player player = game.getPlayer();

    @Test
    void if_an_item_is_spawned_then_the_amount_of_spawned_items_should_increase(){
        spawner.spawnItem();
        assertTrue(spawner.getSpawnedItems().size() > 0);
    }

    @Test
    void if_an_item_is_cleared_then_the_amount_of_spawned_items_should_decrease(){
        spawner.spawnItem();
        int amount = spawner.getSpawnedItems().size();
        IItem item = spawner.getSpawnedItems().get(0);
        spawner.clearItem(item);
        assertTrue(spawner.getSpawnedItems().size() < amount);
    }

    @Test
    void if_player_consumes_50_random_items_both_its_health_and_money_should_have_increased(){
        for (int i = 0; i < 50; i++){
            spawner.spawnItem();
        }
        player.damageTaken(100);
        int health = player.getHealth();
        int money = player.getMoney();
        for (IItem item : spawner.getSpawnedItems()) {
            item.consume(player);
        }
        assertTrue(player.getHealth() > health && player.getMoney() > money);

    }

}
