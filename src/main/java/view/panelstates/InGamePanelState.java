package view.panelstates;

import controllers.KeyClickedController;
import controllers.PlayerController;
import helperclasses.Vector2;
import main.Game;
import sprites.Bullet;
import view.Camera;
import view.HUD;
import view.MainPanel;
import view.drawers.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InGamePanelState implements IPanelState {

    private final Game game;
    private final HUD hud;
    private final ArrayList<IDrawer> drawers;
    private final MainPanel mainPanel;

    private final Camera camera;

    private final ArrayList<KeyListener> keyListeners;

    long bulletTimer;

    Bullet bullet;

    boolean bulletFocused;

    BulletDrawer bulletDrawer;

    public InGamePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();

        hud = new HUD(game.getPlayer());
        camera = new Camera(game.getPlayer());

        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game));
        keyListeners.add(new KeyClickedController(game));

        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer(), camera));
        drawers.add(new EnemyDrawer(game.getEnemies(), camera));
        drawers.add(new TileDrawer(game.getTiles(), camera));


        bulletTimer = 0;
        bulletFocused = true;
        bullet = new Bullet(new Vector2(0, 0), new Vector2(0, 0));
        bulletDrawer = new BulletDrawer(bullet, camera);
        drawers.add(bulletDrawer);
    }


    @Override
    public void draw(Graphics2D g) {
        bulletTimer -= 1;

        bullet.update();
        camera.update();
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }
        hud.update(g, mainPanel);

        if (game.getSpacePressed() && bulletTimer <= 0) {
            bulletFocused = true;
            game.resetSpacePressed();
            bulletTimer = 120;
            bullet = new Bullet(game.getPlayer().getCenter(), new Vector2(10, 0));
            bulletDrawer.changeBullet(bullet);
            camera.halfDragEffectConstant();
            camera.zoom();
            camera.setFocusedObject(bullet);
        }

        if (bulletFocused && bulletTimer <= 0) {
            bulletFocused = false;
            camera.resetZoom();
            camera.resetDragEffectConstant();
            camera.setFocusedObject(game.getPlayer());
        }
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
