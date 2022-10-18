package gameobjects.Player;

import model.helperclasses.EDirection;
import model.Game;
import model.gameobjects.Player;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player;
    @BeforeEach
    void init(){
        this.player = new Player(48, 48, new Tile[0][0]);
    }
    @Test
    void test_getPosX_returns_player_current_x_position(){
        //Player starting position is x=48
        assertEquals(48, player.getPosX());
    }
    @Test
    void test_getPosY_returns_player_current_Y_position(){
        //Player starting position is y=48
        assertEquals(48, player.getPosY());
    }
    @Test
    void test_setPosX_sets_player_x_position(){
        //Player starting position is x=48
        player.setPosX(96);
        assertEquals(96, player.getPosX());
    }
    @Test
    void test_setPosY_sets_player_y_position(){
        //Player starting position is x=48
        player.setPosY(96);
        assertEquals(96, player.getPosY());
    }
    @Test
    void test_getPos_returns_vector2_with_player_current_pos(){
        //Player starting pos is x=48, y=48
        Vector2 testPosVector = new Vector2(48, 48);
        assertEquals(testPosVector.getX(), player.getPos().getX());
        assertEquals(testPosVector.getY(),player.getPos().getY());
    }
    @Test
    void test_setPos_sets_vector2_with_x_and_y_values(){
        player.setPos(new Vector2(10, 10));
        assertEquals(10, player.getPosX());
        assertEquals(10, player.getPosY());
    }

    @Test
    void test_getVel_returns_vector2_with_player_current_vel(){
        //Player starting vel is x=0, y=0
        Vector2 testVelVector = new Vector2(0, 0);
        assertEquals(testVelVector.getX(), player.getVel().getX());
        assertEquals(testVelVector.getY(),player.getVel().getY());
    }
    @Test
    void test_setVel_sets_vector2_with_x_and_y_values(){
        player.setVel(new Vector2(10, 10));
        assertEquals(10, player.getVelX());
        assertEquals(10, player.getVelY());
    }

    @Test
    void test_getAcc_returns_vector2_with_player_current_acc(){
        //Player starting acc is x=0, y=0
        Vector2 testAccVector = new Vector2(0, 0);
        assertEquals(testAccVector.getX(), player.getAcc().getX());
        assertEquals(testAccVector.getY(),player.getAcc().getY());
    }
    @Test
    void test_setAcc_sets_vector2_with_x_and_y_values(){
        player.setAcc(new Vector2(10, 10));
        assertEquals(10, player.getAccX());
        assertEquals(10, player.getAccY());
    }
    @Test
    void test_getVelX_returns_player_current_velX(){
        //Player starts with velX = 0
        assertEquals(0, player.getVelX());
    }

    @Test
    void test_getVelY_returns_player_current_velY(){
        //Player starts with velY = 0
        assertEquals(0, player.getVelY());
    }
    @Test
    void test_setVelX_sets_sets_velX(){
        //Player starts with velX = 0;
        player.setVelX(1);
        assertEquals(1, player.getVelX());
    }

    @Test
    void test_setVelY_sets_sets_velY(){
        //Player starts with velY = 0;
        player.setVelY(1);
        assertEquals(1, player.getVelY());
    }
    @Test
    void test_getAccX_returns_player_current_accX(){
        //Player starts with accX = 0
        assertEquals(0, player.getAccX());
    }

    @Test
    void test_getAccY_returns_player_current_accY(){
        //Player starts with accY = 0
        assertEquals(0, player.getAccY());
    }
    @Test
    void test_setAccX_sets_sets_accX(){
        //Player starts with accX = 0;
        player.setAccX(1);
        assertEquals(1, player.getAccX());
    }

    @Test
    void test_setAccY_sets_sets_accY(){
        //Player starts with accY = 0;
        player.setAccY(1);
        assertEquals(1, player.getAccY());
    }

    @Test
    void test_stopCurrentMovement_sets_acc_and_vel_to_zero(){
        //Sets acc and vel
        player.setAcc(new Vector2(1, 1));
        player.setVel(new Vector2(1, 1));

        player.stopAllCurrentMovement();
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
        //Player starts with 1000 money
        assertEquals(1000, player.getMoney());
    }
    @Test
    void test_addMoney_increments_money_with_amount(){
        //Player starts with 1000 money
        player.addMoney(10);
        assertEquals(1010, player.getMoney());
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
        Game game = new Game();
        game.getPlayer().shoot(game);
        assertEquals(1, game.getProjectiles().size());
    }
    @Test
    void test_shoot_gets_current_direction_if_direction_does_not_equal_not_moving(){
        Game game = new Game();
        game.getPlayer().setDirection(EDirection.UP);
        List<Projectile> projectiles = new ArrayList<>();
        game.getPlayer().shoot(game);
    }
}
