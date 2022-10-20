import config.Config;
import model.Game;
import model.gameobjects.Player;
import model.Vector2;
import org.junit.jupiter.api.Test;
import view.Camera;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for camera.
 */
public class CameraTest {

    Game game;
    Player player;
    Camera camera = Camera.getInstance();


    @Test
    void test_camera_not_moving_when_not_focusing_object() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        Vector2 offsetWhenPlayerAt0x0 = camera.getOffset();
        player.setPos(new Vector2(50, 50));
        Vector2 offsetWhenPlayerAt50x50 = camera.getOffset();
        assertEquals(offsetWhenPlayerAt0x0.getX(), offsetWhenPlayerAt50x50.getX());
        assertEquals(offsetWhenPlayerAt0x0.getY(), offsetWhenPlayerAt50x50.getY());
    }

    @Test
    void test_camera_moving_when_focusing_object_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetWhenPlayerAt0x0 = camera.getOffset();

        player.setPos(new Vector2(50, 50));
        camera.update();
        Vector2 offsetWhenPlayerAt50x50 = camera.getOffset();

        assertTrue(offsetWhenPlayerAt0x0.getX() < offsetWhenPlayerAt50x50.getX());
    }

    @Test
    void test_camera_moving_when_focusing_object_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetWhenPlayerAt0x0 = camera.getOffset();

        player.setPos(new Vector2(50, 50));
        camera.update();
        Vector2 offsetWhenPlayerAt50x50 = camera.getOffset();

        assertTrue(offsetWhenPlayerAt0x0.getY() < offsetWhenPlayerAt50x50.getY());
    }

    @Test
    void test_get_camera_center() {
        Vector2 offset = camera.getOffset();
        Vector2 center = camera.getCenter();
        assertEquals(center.getX(), offset.getX() + Config.SCREEN_WIDTH / 2.0);
        assertEquals(center.getY(), offset.getY() + Config.SCREEN_HEIGHT / 2.0);
    }

    @Test
    void test_camera_zoom_in() {
        camera.reset();
        camera.zoomIn();
        assertEquals(1.01, camera.getZoomMultiplier());
    }

    @Test
    void test_camera_zoom_out() {
        camera.reset();
        camera.zoomIn();
        camera.zoomOut();
        assertEquals(1, camera.getZoomMultiplier());
    }

    /**
     * startZoomIn method in camera takes time to complete due to it zooming in over time.
     * Need to put the thread to sleep to test it
     */
    @Test
    void test_camera_start_zoom_in() {
        camera.reset();
        camera.startZoomIn();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        assertEquals(2.5, camera.getZoomMultiplier());
    }

    /**
     * startZoomIn method in camera takes time to complete due to it zooming in over time.
     * Need to put the thread to sleep to test it
     */
    @Test
    void test_camera_start_zoom_out() {
        camera.reset();
        for (int i = 0; i < 50; i++) {
            camera.zoomIn();
        }
        camera.startZoomOut();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        assertEquals(1, camera.getZoomMultiplier());
    }

    @Test
    void testCameraLowerZoomBoundarie() {
        camera.reset();
        camera.zoomOut();
        camera.zoomOut();
        camera.zoomOut();
        assertEquals(1, camera.getZoomMultiplier());
    }

    @Test
    void testCameraUpperZoomBoundarie() {
        camera.reset();
        for (int i = 0; i < 200; i++) {
            camera.zoomIn();
        }
        assertEquals(2.5, camera.getZoomMultiplier());
    }

    @Test
    void test_camera_glide_effect_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs1 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(100);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs100 = camera.getOffset();

        assertTrue(offsetAfterOneUpdateWhenDragIs1.getX() < offsetAfterOneUpdateWhenDragIs100.getX());
    }

    @Test
    void test_camera_glide_effect_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs1 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(100);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs100 = camera.getOffset();

        assertTrue(offsetAfterOneUpdateWhenDragIs1.getY() < offsetAfterOneUpdateWhenDragIs100.getY());
    }

    @Test
    void test_camera_reset_glide_effect_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs50 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(100);
        camera.resetGlideConstant();
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIsReset = camera.getOffset();

        assertEquals(offsetAfterOneUpdateWhenDragIs50.getX(), offsetAfterOneUpdateWhenDragIsReset.getX());
    }

    @Test
    void test_camera_reset_glide_effect_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs50 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(100);
        camera.resetGlideConstant();
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIsReset = camera.getOffset();

        assertEquals(offsetAfterOneUpdateWhenDragIs50.getY(), offsetAfterOneUpdateWhenDragIsReset.getY());
    }

    @Test
    void test_camera_drag_effect_boundaries_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs1 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(-10);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIsLessThan1 = camera.getOffset();

        assertEquals(offsetAfterOneUpdateWhenDragIs1.getX(), offsetAfterOneUpdateWhenDragIsLessThan1.getX());
    }

    @Test
    void test_camera_drag_effect_boundaries_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIs1 = camera.getOffset();

        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(-10);
        camera.update();
        Vector2 offsetAfterOneUpdateWhenDragIsLessThan1 = camera.getOffset();

        assertEquals(offsetAfterOneUpdateWhenDragIs1.getY(), offsetAfterOneUpdateWhenDragIsLessThan1.getY());
    }

    @Test
    void test_camera_border_limitation_top_left_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getX());
    }

    @Test
    void test_camera_border_limitation_top_left_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getY());
    }

    @Test
    void test_camera_border_limitation_top_right_X() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_WIDTH, offset.getX());
    }

    @Test
    void test_camera_border_limitation_top_right_Y() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getY());
    }

    @Test
    void test_camera_border_limitation_bottom_left_X() {
        game = new Game();
        player = new Player(0, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getX());
    }

    @Test
    void test_camera_border_limitation_bottom_left_Y() {
        game = new Game();
        player = new Player(0, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_HEIGHT, offset.getY());
    }

    @Test
    void test_camera_border_limitation_bottom_right_X() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_WIDTH, offset.getX());
    }

    @Test
    void test_camera_border_limitation_bottom_right_Y() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_HEIGHT, offset.getY());
    }

    @Test
    void test_camera_border_limitation_remove_border_X() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offsetWithBorderLimit = camera.getOffset();
        camera.removeBorderLimit();
        camera.update();
        Vector2 offsetWithoutBorderLimit = camera.getOffset();
        assertTrue(offsetWithoutBorderLimit.getX() < offsetWithBorderLimit.getX());
    }

    @Test
    void test_camera_border_limitation_remove_border_Y() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offsetWithBorderLimit = camera.getOffset();
        camera.removeBorderLimit();
        camera.update();
        Vector2 offsetWithoutBorderLimit = camera.getOffset();
        assertTrue(offsetWithoutBorderLimit.getY() < offsetWithBorderLimit.getY());
    }
}
