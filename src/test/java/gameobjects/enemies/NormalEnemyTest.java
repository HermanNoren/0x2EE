package gameobjects.enemies;


import model.gameobjects.Player;
import model.gameobjects.enemies.EEnemyType;
import model.gameobjects.enemies.Enemy;
import model.gameobjects.enemies.EnemyFactory;
import model.gameobjects.enemies.NormalEnemyFactory;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalEnemyTest {
    private Enemy normalEnemy;
    @BeforeEach
    void init(){
        GameMap gameMap = new GameMap(10, 10, false);
        Player player = new Player(1, 1, gameMap.getGameMapCoordinates());
        EnemyFactory enemyFactory = new NormalEnemyFactory();
        normalEnemy= enemyFactory.createEnemy(player, 1, 100, gameMap.getPassableTiles().get(2), gameMap.getGameMapCoordinates());
    }

    @Test
    void test_getDamage_returns_damage(){
        assertEquals(1, normalEnemy.getDamage());
    }
    @Test
    void test_getType_returns_normal(){
        assertEquals(EEnemyType.NORMAL, normalEnemy.getType());
    }
    @Test
    void test_getKillReward_returns_100(){
        assertEquals(100, normalEnemy.getKillReward());
    }

}
