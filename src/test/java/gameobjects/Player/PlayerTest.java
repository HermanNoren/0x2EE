package gameobjects.Player;

import controllers.EDirection;
import model.Game;
import model.gameobjects.Player;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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
    void test_stopCurrentMovement_sets_acc_and_vel_to_zero(){
        //Sets acc and vel
        player.setAcc(new Vector2(1, 1));
        player.setVel(new Vector2(1, 1));

        player.stopCurrentMovement();
        assertEquals(0, player.getAcc().getX());
        assertEquals(0, player.getAcc().getY());
        assertEquals(0, player.getVel().getY());
        assertEquals(0, player.getVel().getY());
    }

    @Test
    void test_if_player_x_pos_is_increased_when_moveX_is_called_and_direction_equals_right(){
        player.setDirection(EDirection.RIGHT);
        double prevPosX = player.getPosX();
        player.moveX(1);
        assertEquals(prevPosX + 0.5, player.getPosY(), 0.5);
    }
    @Test
    void test_if_player_x_pos_is_decreased_when_moveX_is_called_and_direction_equals_left(){
        player.setDirection(EDirection.LEFT);
        double prevPosX = player.getPosX();
        player.moveX(1);
        assertEquals(prevPosX - 0.5, player.getPosX(), 0.5);
    }
    @Test
    void test_if_player_y_pos_is_increased_when_moveY_is_called_and_direction_equals_down(){
        player.setDirection(EDirection.DOWN);
        double prevPosY = player.getPosY();
        player.moveY(1);
        assertEquals(prevPosY + 0.5, player.getPosY(), 0.5);
    }
    @Test
    void test_if_player_Y_pos_is_decreased_when_moveY_is_called_and_direction_equals_up(){
        player.setDirection(EDirection.UP);
        double prevPosY = player.getPosY();
        player.moveY(1);
        assertEquals(prevPosY - 0.5, player.getPosY(), 0.5);
    }

    @Test
    void test_getMoney_returns_current_amount_of_money_player_possesses(){
        //Player starts with 0 money
        assertEquals(0, player.getMoney());
    }
    @Test
    void test_addMoney_increments_money_with_amount(){
        //Player starts with 0 money
        player.addMoney(10);
        assertEquals(10, player.getMoney());
    }
    @Test
    void test_getScore_returns_player_current_score(){
        //Player starts with score 0
        assertEquals(0, player.getScore());
    }
    @Test
    void test_addScore_increments_score_with_amount(){
        //Player starts with score 0
        player.addScore(10);
        assertEquals(10, player.getScore());
    }

    @Test
    void test_shoot_adds_new_projectile_to_list_in_game(){
        player.shoot();
        game.update(1);
        List<Projectile> projectileList = game.getProjectiles();
        assertEquals(1, projectileList.size());
    }
    @Test
    void test_shoot_gets_current_direction_if_direction_does_not_equal_not_moving(){
        player.setDirection(EDirection.UP);
        player.shoot();
    }

}
