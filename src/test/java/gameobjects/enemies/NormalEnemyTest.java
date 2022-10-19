package gameobjects.enemies;

import model.Game;
import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalEnemyTest {
    private Enemy normalEnemy;
    @BeforeEach
    void init(){
        Game game = new Game();
        Player player = new Player(1, 1, game.getGameMap().getGameMapCoordinates());
        EnemyFactory enemyFactory = new NormalEnemyFactory();
        normalEnemy= enemyFactory.createEnemy(player, 1, 100,game.getGameMap().getPassableTiles(), game.getGameMap().getGameMapCoordinates());
    }

    @Test
    void test_getDamage_returns_damage(){
        assertEquals(1, normalEnemy.getDamage());
    }
    @Test
    void test_getType_returns_normal(){
        assertEquals("normal", normalEnemy.getType());
    }
    @Test
    void test_getKillReward_returns_100(){
        assertEquals(100, normalEnemy.getSCoreReward());
    }

}
