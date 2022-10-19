package gameobjects.ItemSpawner;
import model.Game;
import model.gameobjects.ItemSpawner.Coin;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.Player;
import model.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    Game game = new Game();

    Player player = game.getPlayer();


    @Test
    void test_if_player_gets_more_money_if_consuming_a_coin(){
        int money = player.getMoney();
        IItem coin = new Coin(new Vector2(20, 20));
        coin.consume(player);
        assertTrue(player.getMoney() > money);

    }

    @Test
    void test_if_getType_returns_type_of_item(){
        IItem coin = new Coin(new Vector2(20, 20));
        assertEquals("coin", coin.getType());
    }

    @Test
    void test_getCenter_x_position(){
        IItem coin = new Coin(new Vector2(10, 20));
        assertEquals(10 + coin.getWidth() / 2, coin.getCenter().getX());

    }

    @Test
    void test_getCenter_y_position(){
        IItem coin = new Coin(new Vector2(10, 20));
        assertEquals(20 + coin.getHeight()/2 ,coin.getCenter().getY());

    }

}
