package weapons;

import model.Game;
import model.upgradables.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void test_if_reload_when_out_of_ammo(){
        for (int i = 0; i < 11; i++) {
            game.makePlayerShoot();
        }

        // TODO: Will change as we change player logic
        assertEquals(10, game.getProjectiles().size());
    }

    @Test
    void test_if_reloading(){
        // TODO: Will change as we implement reloading logic
        Weapon weapon = new Weapon();
        assertFalse(weapon.isReloading());
    }
}
