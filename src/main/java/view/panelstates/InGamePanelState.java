package view.panelstates;

import config.Config;
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

    private Game game;
    private HUD hud;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    private final Camera camera;

    private final ArrayList<KeyListener> keyListeners;

    public InGamePanelState() {
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController());
        keyListeners.add(new KeyClickedController());
        hud = new HUD(game.getPlayer());
        camera = new Camera();
        camera.setFocusedObject(game.getPlayer());
        keyListeners.add(new CameraController(camera));

        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer(), camera));
        drawers.add(new EnemyDrawer(game.getEnemies(), camera));
        drawers.add(new TerrainDrawer(game.getTerrainBorder(), camera));
    }


    @Override
    public void draw(Graphics2D g) {
        camera.update();
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }


        hud.update(g);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
