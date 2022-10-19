package weapons;

import model.Game;
import model.upgradables.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Weapon.
 */
public class WeaponTest {
    Game game;

    @BeforeEach
    void init(){
        game = new Game();
    }

    @Test
    void test_if_shoot_creates_projectile(){
        for (int i = 0; i < 3; i++) {
            game.makePlayerShoot();
        }

        assertEquals(3, game.getProjectiles().size());
    }
}
