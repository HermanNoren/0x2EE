package gameobjects.enemies;

import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {

    private Enemy enemy;
    private EnemyFactory enemyFactory;
    private GameMap gameMap;
    private Player player;
    @BeforeEach
    void init(){
        gameMap = new GameMap(10, 10);
        player = new Player(1, 1, gameMap.getGameMapCoordinates());
        enemyFactory = new NormalEnemyFactory();
        enemy = enemyFactory.createEnemy(player, gameMap.getPassableTiles(), gameMap.getGameMapCoordinates());
    }
    @Test
    void test_update_moves_enemy_towards_target_entity(){
        Vector2 startPosEnemy = enemy.getPos();
        double startDeltaX = Math.abs(startPosEnemy.getX() - player.getPos().getX());
        double startDeltaY = Math.abs(startPosEnemy.getY() - player.getPos().getY());
        enemy.update(1);

        double deltaX = Math.abs(startPosEnemy.getX() - player.getPos().getX());
        double deltaY = Math.abs(startPosEnemy.getY() - player.getPos().getY());

        assertTrue(startDeltaY >= deltaY);
        assertTrue(startDeltaX >= deltaX);
    }
}
