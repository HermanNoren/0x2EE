import config.Config;
import model.Game;
import model.gameobjects.Player;
import model.Vector2;
import org.junit.jupiter.api.Test;
import view.Camera;

import static org.junit.jupiter.api.Assertions.*;

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
    void test_camera_moving_when_focusing_object() {
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
    void test_camera_zoom() {
        assertEquals(1, camera.getZoomMultiplier());
        camera.zoomIn();
        assertEquals(1.05, camera.getZoomMultiplier());
        camera.zoomOut();
        assertEquals(1, camera.getZoomMultiplier());
    }

    @Test
    void test_camera_zoom_boundaries() {
        for (int i = 0; i < 20; i++) {
            camera.zoomOut();
        }
        assertEquals(1, camera.getZoomMultiplier());
        for (int i = 0; i < 40; i++) {
            camera.zoomIn();
        }
        assertEquals(4, camera.getZoomMultiplier());
        for (int i = 0; i < 10; i++) {
            camera.zoomIn();
        }
        assertEquals(4, camera.getZoomMultiplier());
        for (int i = 0; i < 40; i++) {
            camera.zoomOut();
        }
        assertEquals(1, camera.getZoomMultiplier());
    }

    @Test
    void test_camera_drag_effect() {
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
        assertTrue(offsetAfterOneUpdateWhenDragIs1.getY() < offsetAfterOneUpdateWhenDragIs100.getY());
    }

    @Test
    void test_camera_reset_drag_effect() {
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
        assertEquals(offsetAfterOneUpdateWhenDragIs50.getY(), offsetAfterOneUpdateWhenDragIsReset.getY());
    }

    @Test
    void test_camera_drag_effect_boundaries() {
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
        assertEquals(offsetAfterOneUpdateWhenDragIs1.getY(), offsetAfterOneUpdateWhenDragIsLessThan1.getY());
    }

    @Test
    void test_camera_border_limitation_top_left() {
        game = new Game();
        player = new Player(0, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getX());
        assertEquals(0, offset.getY());
    }

    @Test
    void test_camera_border_limitation_top_right() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, 0, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_WIDTH, offset.getX());
        assertEquals(0, offset.getY());
    }

    @Test
    void test_camera_border_limitation_bottom_left() {
        game = new Game();
        player = new Player(0, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(0, offset.getX());
        assertEquals(Config.SCREEN_HEIGHT, offset.getY());
    }

    @Test
    void test_camera_border_limitation_bottom_right() {
        game = new Game();
        player = new Player(Config.SCREEN_WIDTH * 2, Config.SCREEN_HEIGHT * 2, game.getGameMap().getGameMapCoordinates());
        camera.reset();
        camera.setFocusedObject(player);
        camera.setGlideConstant(1);
        camera.setBorderLimit(0, Config.SCREEN_WIDTH * 2, 0, Config.SCREEN_HEIGHT * 2);
        camera.update();
        Vector2 offset = camera.getOffset();
        assertEquals(Config.SCREEN_WIDTH, offset.getX());
        assertEquals(Config.SCREEN_HEIGHT, offset.getY());
    }

    @Test
    void test_camera_border_limitation_remove_border() {
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
        assertTrue(offsetWithoutBorderLimit.getY() < offsetWithBorderLimit.getY());
    }
}
