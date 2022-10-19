package gameobjects.enemies;

import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

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
        enemy = enemyFactory.createEnemy(player, gameMap.getPassableTiles(), gameMap.getGameMapCoordinates());
    }

    @Test
    void test_getTargetEntity_returns_targetEntity(){
        assertSame(enemy.getTargetEntity(), player);
    }

    @Test
    void test_update_moves_enemy_towards_target_entity_x_position(){

        enemy.setPos(new Vector2(100, 100));
        player.setPos(new Vector2(enemy.getPosX()+100, enemy.getPosY()));
        double prevDeltaX = Math.abs(player.getPosX() - enemy.getPosX());

        enemy.update(4);

        double deltaX = Math.abs(player.getPosX() - enemy.getPosX());

        assertTrue(prevDeltaX > deltaX);
    }


}
