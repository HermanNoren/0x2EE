package helperclasses.collision;

import model.Game;
import model.gameobjects.IGameObject;
import model.gameobjects.Player;
import model.helperclasses.collision.CollisionHandler;
import model.helperclasses.collision.ECollisionAxis;
import model.mapclasses.Terrain;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionHandlerTest {

    Game game;
    Player player1;
    IGameObject player2;

    /*
        Tests for CollisionHandler.testCollision(). Testing collision on all sides and corners of an object when
        a width / height of 1 unit is intersecting and testing when the objects are perfectly aligned but not actually
        colliding.
     */

    @Test
    void test_test_collision_true_left_side() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth() + 1, 64, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_right_side() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth() - 1, 64, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_top_side() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 - player1.getHeight() + 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_bottom_side() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 + player1.getHeight() - 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_top_left_corner() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth() + 1, 64 - player1.getHeight() + 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_top_right_corner() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth() - 1, 64 - player1.getHeight() + 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_bottom_left_corner() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth() + 1, 64 + player1.getHeight() - 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_true_bottom_right_corner() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth() - 1, 64 + player1.getHeight() - 1, game);
        assertTrue(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_left() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth(), 64, game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_right() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth(), 64, game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_top() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 - player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_bottom() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 + player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_top_left() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth(), 64 - player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_top_right() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth(), 64 - player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_bottom_left() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth(), 64 + player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }

    @Test
    void test_test_collision_false_bottom_right() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth(), 64 + player1.getHeight(), game);
        assertFalse(CollisionHandler.testCollision(player1, player2));
    }


    /*
        Tests for CollisionHandler.getCollisionDirection() with player1 as the moving object and player2 as the
        object that player1 is colliding into.
     */

    @Test
    void test_directional_collision_X_AXIS_left() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 - player1.getWidth(), 64, game);
        player1.setVelX(-1);
        player1.moveX(1);
        Map<String, Boolean> collisionDirection = CollisionHandler.getCollisionDirection(player1, player2, ECollisionAxis.X_AXIS);
        assertTrue(collisionDirection.get("left"));
        assertFalse(collisionDirection.get("right"));
        assertFalse(collisionDirection.get("top"));
        assertFalse(collisionDirection.get("bottom"));
    }

    @Test
    void test_directional_collision_X_AXIS_right() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64 + player1.getWidth(), 64, game);
        player1.setVelX(1);
        player1.moveX(1);
        Map<String, Boolean> collisionDirection = CollisionHandler.getCollisionDirection(player1, player2, ECollisionAxis.X_AXIS);
        assertFalse(collisionDirection.get("left"));
        assertTrue(collisionDirection.get("right"));
        assertFalse(collisionDirection.get("top"));
        assertFalse(collisionDirection.get("bottom"));
    }

    @Test
    void test_directional_collision_Y_AXIS_top() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 - player1.getHeight(), game);
        player1.setVelY(-1);
        player1.moveY(1);
        Map<String, Boolean> collisionDirection = CollisionHandler.getCollisionDirection(player1, player2, ECollisionAxis.Y_AXIS);
        assertFalse(collisionDirection.get("left"));
        assertFalse(collisionDirection.get("right"));
        assertTrue(collisionDirection.get("top"));
        assertFalse(collisionDirection.get("bottom"));
    }

    @Test
    void test_directional_collision_Y_AXIS_bottom() {
        game = new Game();
        player1 = new Player(64, 64, game);
        player2 = new Player(64, 64 + player1.getHeight(), game);
        player1.setVelY(1);
        player1.moveY(1);
        Map<String, Boolean> collisionDirection = CollisionHandler.getCollisionDirection(player1, player2, ECollisionAxis.Y_AXIS);
        assertFalse(collisionDirection.get("left"));
        assertFalse(collisionDirection.get("right"));
        assertFalse(collisionDirection.get("top"));
        assertTrue(collisionDirection.get("bottom"));
    }


    /*
        Tests for CollisionHandler.getSpecificTerrainCollisions().
        A small 4x4 game map will be used in the testing:
        '''''''''''
        ' W W W W '
        ' W       '
        ' W       '
        ' W       '
        '''''''''''
        Where "W" is a placeholder for a non-passable terrain piece and whitespace are passable terrain pieces.
     */

    @Test
    void test_specific_terrain_collisions_no_collision() {
        game = new Game();
        player1 = new Player(48, 48, game);
        List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player1, createSmallTerrainMatrix());
        assertEquals(0, collidedTerrain.size());
    }

    @Test
    void test_specific_terrain_collisions_with_collision_to_one_terrain_piece() {
        game = new Game();
        player1 = new Player(48, 48, game);
        player1.setVelX(-1);
        player1.moveX(1);
        List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player1, createSmallTerrainMatrix());
        assertEquals(1, collidedTerrain.size());
    }

    @Test
    void test_specific_terrain_collisions_with_collision_with_top_left_three_terrain_pieces() {
        game = new Game();
        player1 = new Player(48, 48, game);
        player1.setVelX(-1);
        player1.moveX(1);
        player1.setVelY(-1);
        player1.moveY(1);
        List<Terrain> collidedTerrain = CollisionHandler.getSpecificTerrainCollisions(player1, createSmallTerrainMatrix());
        assertEquals(3, collidedTerrain.size());
    }

    /**
     * Creates a 4x4 matrix containing terrain pieces. Terrain in the top row and the left column are non-passable.
     * @return 4x4 terrain matrix
     */
    private static Terrain[][] createSmallTerrainMatrix() {
        Terrain[][] terrain = {
                {new Terrain(0, 0), new Terrain(1, 0), new Terrain(2, 0), new Terrain(3, 0)},
                {new Terrain(0, 1), new Terrain(1, 1), new Terrain(2, 1), new Terrain(3, 1)},
                {new Terrain(0, 2), new Terrain(1, 2), new Terrain(2, 2), new Terrain(3, 2)},
                {new Terrain(0, 3), new Terrain(1, 3), new Terrain(2, 3), new Terrain(3, 3)}
        };

        for (int row = 0; row < terrain.length; row++) {
            for (int col = 0; col < terrain[row].length; col++) {
                if (row == 0 || col == 0) {
                    terrain[row][col].setPassable(false);
                }
            }
        }

        return terrain;
    }
}
