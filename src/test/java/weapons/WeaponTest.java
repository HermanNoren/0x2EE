package weapons;

import controllers.EDirection;
import model.Game;
import model.gameobjects.Player;
import model.gameobjects.Projectile;
import model.helperclasses.Vector2;
import model.weapons.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
    Game game;
    Player player;

    @BeforeEach
    void init(){
        game = new Game();
        player = game.getPlayer();
    }

    @Test
    void test_if_shoot_creates_projectile(){
        for (int i = 0; i < 3; i++) {
            game.makePlayerShoot();
            game.update(1);
        }

        assertTrue(game.getProjectiles().size() == 3);
    }

    @Test
    void test_if_reload_when_out_of_ammo(){
        for (int i = 0; i < 11; i++) {
            game.makePlayerShoot();
            game.update(1);
        }

        // TODO: Will change as we change player logic
        assertTrue(game.getProjectiles().size() == 10);
    }

    @Test
    void test_if_reloading(){
        // TODO: Will change as we implement reloading logic
        Weapon weapon = new Weapon(1, 1);
        assertFalse(weapon.isReloading());
    }
}
