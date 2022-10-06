package gameobjects.Player;

import controllers.EDirection;
import model.Game;
import model.gameobjects.Player;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player;
    private Game game;
    @BeforeEach
    void init(){
        this.game = new Game();
        this.player = new Player(48, 48, game);
    }

    @Test
    void test_if_player_x_pos_is_increased_when_moveX_is_called_and_direction_equals_right(){
        player.setDirection(EDirection.RIGHT);
        double prevLocX = player.getPosX();
        player.moveX(1);
        assertTrue(player.getPosX() > prevLocX);
    }
    @Test
    void test_if_player_x_pos_is_decreased_when_moveX_is_called_and_direction_equals_left(){
        player.setDirection(EDirection.LEFT);
        double prevLocX = player.getPosX();
        player.moveX(1);
        assertTrue(player.getPosX() < prevLocX);
    }
    @Test
    void test_if_player_y_pos_is_increased_when_moveY_is_called_and_direction_equals_down(){
        player.setDirection(EDirection.DOWN);
        double prevPosY = player.getPosY();
        player.moveY(0);
        assertEquals(prevPosY + 0.5, player.getPosY(), 0.5);
    }
    @Test
    void test_if_player_Y_pos_is_decreased_when_moveY_is_called_and_direction_equals_up(){
        player.setDirection(EDirection.UP);
        double prevLocY = player.getPosY();
        player.moveY(1);
        assertTrue(player.getPosY() < prevLocY);
    }
}
