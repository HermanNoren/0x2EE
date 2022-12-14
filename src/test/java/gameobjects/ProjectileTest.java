package gameobjects;

import model.gameobjects.EDirection;
import model.gameobjects.Projectile;
import model.Vector2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for projectile.
 */
public class ProjectileTest {

    Projectile projectile;

    @Test
    void test_get_width() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.NOT_MOVING);
        assertEquals(8, projectile.getWidth());
    }

    @Test
    void test_getDirection_returns_projectile_direction(){
        projectile = new Projectile(new Vector2(0, 0), EDirection.UP);
        assertEquals(EDirection.UP, projectile.getDirection());
    }
    @Test
    void test_get_height() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.NOT_MOVING);
        assertEquals(8, projectile.getHeight());
    }

    @Test
    void test_get_pos_x_value() {
        projectile = new Projectile(new Vector2(10, 15), EDirection.NOT_MOVING);
        assertEquals(10, projectile.getPos().getX());
    }

    @Test
    void test_get_pos_y_value() {
        projectile = new Projectile(new Vector2(10, 15), EDirection.NOT_MOVING);
        assertEquals(15, projectile.getPos().getY());
    }

    @Test
    void test_get_center_x_value() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.NOT_MOVING);
        assertEquals(4, projectile.getCenter().getX());
    }

    @Test
    void test_get_center_y_value() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.NOT_MOVING);
        assertEquals(4, projectile.getCenter().getY());
    }


    @Test
    void test_move_projectile_left() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.LEFT);
        projectile.update(1);
        assertEquals(-10, projectile.getPos().getX());
        assertEquals(0, projectile.getPos().getY());
    }

    @Test
    void test_move_projectile_right() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.RIGHT);
        projectile.update(1);
        assertEquals(10, projectile.getPos().getX());
        assertEquals(0, projectile.getPos().getY());
    }

    @Test
    void test_move_projectile_up() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.UP);
        projectile.update(1);
        assertEquals(0, projectile.getPos().getX());
        assertEquals(-10, projectile.getPos().getY());
    }

    @Test
    void test_move_projectile_down() {
        projectile = new Projectile(new Vector2(0, 0), EDirection.DOWN);
        projectile.update(1);
        assertEquals(0, projectile.getPos().getX());
        assertEquals(10, projectile.getPos().getY());
    }
}
