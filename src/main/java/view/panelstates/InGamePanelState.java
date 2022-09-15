package view.panelstates;

import controllers.CameraController;
import controllers.KeyClickedController;
import controllers.PlayerController;
import main.Game;
import view.Camera;
import view.HUD;
import view.MainPanel;
import view.drawers.EnemyDrawer;
import view.drawers.IDrawer;
import view.drawers.PlayerDrawer;
import view.drawers.TerrainDrawer;

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

    public InGamePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();

        hud = new HUD(game.getPlayer());
        camera = new Camera();
        camera.setFocusedObject(game.getPlayer());

        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game));
        keyListeners.add(new KeyClickedController(game));
        keyListeners.add(new CameraController(camera));

        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer(), camera));
        drawers.add(new EnemyDrawer(game.getEnemies(), camera));
        drawers.add(new TerrainDrawer(game.getTerrainBorder()));
    }


    @Override
    public void draw(Graphics2D g) {
        camera.update();
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }
        hud.update(g, mainPanel);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
