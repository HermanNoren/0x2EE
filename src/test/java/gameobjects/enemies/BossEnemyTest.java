package gameobjects.enemies;

import model.Game;
import model.gameobjects.Player;
import model.gameobjects.enemies.BossEnemyFactory;
import model.gameobjects.enemies.EEnemyType;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BossEnemyTest {
    private Enemy bossEnemy;
    @BeforeEach
    void init(){
        Game game = new Game();
        Player player = new Player(1, 1, game.getGameMap().getGameMapCoordinates());
        EnemyFactory enemyFactory = new BossEnemyFactory();
        bossEnemy = enemyFactory.createEnemy(player,5, 500, game.getGameMap().getPassableTiles(), game.getGameMap().getGameMapCoordinates());
    }

    @Test
    void test_getDamage_returns_damage(){
        assertEquals(5, bossEnemy.getDamage());
    }
    @Test
    void test_getType_returns_normal(){
        assertEquals(EEnemyType.BOSS, bossEnemy.getType());
    }
    @Test
    void test_getKillReward_returns_500(){
        assertEquals(500, bossEnemy.getKillReward());
    }
}
