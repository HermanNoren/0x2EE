import model.Game;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.ItemSpawner.Spawner;
import model.gameobjects.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpawnerTest {

    Game game = new Game();
    Spawner spawner = new Spawner(game);

    Player player = game.getPlayer();

    @Test
    void testSpawn(){
        spawner.spawnItem();
        assertTrue(spawner.getSpawnedItems().size() > 0);
    }
    @Test
    void testConsume(){
        spawner.spawnItem();
        player.damageTaken(100);
        int health = player.getHealth();
        int money = player.getMoney();
        IItem item = spawner.getSpawnedItems().get(0);
        item.consume(player);
        assertTrue(player.getHealth() > health && player.getMoney() > money);

    }
}
