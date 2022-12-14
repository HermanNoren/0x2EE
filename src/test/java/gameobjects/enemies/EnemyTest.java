package gameobjects.enemies;

import model.Game;
import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.Vector2;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for enemy.
 */
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {

    private Enemy enemy;
    private EnemyFactory enemyFactory;
    private GameMap gameMap;
    private Player player;
    @BeforeEach
    void init(){
        gameMap = new GameMap(10, 10, false);
        enemyFactory = new NormalEnemyFactory();
        player = new Player(10, 10, gameMap.getGameMapCoordinates());
        enemy = enemyFactory.createEnemy(player, 1, 100, gameMap.getPassableTiles().get(7), gameMap.getGameMapCoordinates());
    }

    @Test
    void test_getTargetEntity_returns_targetEntity(){
        assertSame(enemy.getTargetEntity(), player);
    }

    @Test
    void test_update_moves_right_enemy_towards_target_entity_x_position(){

        enemy.setPos(new Vector2(100, 100));
        player.setPos(new Vector2(enemy.getPosX()+100, enemy.getPosY()));
        double prevDeltaX = Math.abs(player.getPosX() - enemy.getPosX());

        enemy.update(4);

        double deltaX = Math.abs(player.getPosX() - enemy.getPosX());

        assertTrue(prevDeltaX > deltaX);
    }
    @Test
    void test_update_moves_left_enemy_towards_target_entity_x_position(){

        enemy.setPos(new Vector2(200, 100));
        player.setPos(new Vector2(enemy.getPosX()-100, enemy.getPosY()));
        double prevDeltaX = Math.abs(player.getPosX() - enemy.getPosX());

        enemy.update(4);

        double deltaX = Math.abs(player.getPosX() - enemy.getPosX());

        assertTrue(prevDeltaX > deltaX);
    }
    @Test
    void test_update_moves_down_enemy_towards_target_entity_x_position(){

        enemy.setPos(new Vector2(100, 100));
        player.setPos(new Vector2(enemy.getPosX(), enemy.getPosY()+100));
        double prevDeltaY = Math.abs(player.getPosY() - enemy.getPosY());

        enemy.update(4);

        double deltaY = Math.abs(player.getPosY() - enemy.getPosY());

        assertTrue(prevDeltaY > deltaY);
    }
    @Test
    void test_update_moves_up_enemy_towards_target_entity_x_position(){

        enemy.setPos(new Vector2(100, 200));
        player.setPos(new Vector2(enemy.getPosX(), enemy.getPosY()-100));
        double prevDeltaY = Math.abs(player.getPosY() - enemy.getPosY());

        enemy.update(4);

        double deltaY = Math.abs(player.getPosY() - enemy.getPosY());

        assertTrue(prevDeltaY > deltaY);
    }

    @Test
    void test_damageTaken_reduces_enemy_health() {
        int prevHealth = enemy.getHealth();
        enemy.damageTaken(1);
        assertTrue(enemy.getHealth() < prevHealth);
    }


}
