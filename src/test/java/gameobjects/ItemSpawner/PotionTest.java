package gameobjects.ItemSpawner;

import model.Game;
import model.gameobjects.ItemSpawner.Coin;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.ItemSpawner.Potion;
import model.gameobjects.Player;
import model.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for potion.
 */
public class PotionTest {

    Game game = new Game();

    Player player = game.getPlayer();

    @Test
    void test_if_player_gets_more_money_if_consuming_a_coin(){
        player.damageTaken(50);
        int health = player.getHealth();
        IItem potion = new Potion(new Vector2(20, 20));
        potion.consume(player);
        assertTrue(player.getHealth() > health);

    }

    @Test
    void test_if_getType_returns_type_of_item(){
        IItem potion = new Potion(new Vector2(20, 20));
        assertEquals("potion", potion.getType());
    }

    @Test
    void test_getCenter_x_position(){
        IItem potion = new Potion(new Vector2(10, 20));
        assertEquals(10 + potion.getWidth() / 2, potion.getCenter().getX());

    }

    @Test
    void test_getCenter_y_position(){
        IItem potion = new Potion(new Vector2(10, 20));
        assertEquals(20 + potion.getHeight()/2 ,potion.getCenter().getY());

    }

    @Test
    void test_getPos_x_position(){
        IItem potion = new Potion(new Vector2(20, 50));
        assertEquals(20, potion.getPos().getX());

    }

    @Test
    void test_getPos_y_position(){
        IItem potion = new Potion(new Vector2(20, 50));
        assertEquals(50, potion.getPos().getY());

    }

}
