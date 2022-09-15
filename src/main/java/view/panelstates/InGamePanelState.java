package view.panelstates;

import controllers.KeyClickedController;
import controllers.PlayerController;
import main.Game;
import view.Camera;
import view.HUD;
import view.MainPanel;
import view.drawers.EnemyDrawer;
import view.drawers.IDrawer;
import view.drawers.PlayerDrawer;
import view.drawers.TileDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InGamePanelState implements IPanelState {

    private Game game;
    private HUD hud;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    private final Camera camera;

    private ArrayList<KeyListener> keyListeners;

    public InGamePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
        camera = new Camera(game.getPlayer());
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game));
        keyListeners.add(new KeyClickedController(game));
        hud = new HUD(game.getPlayer());
        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer(), camera));
        drawers.add(new EnemyDrawer(game.getEnemies(), camera));
        drawers.add(new TileDrawer(game.getTiles(), camera));
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
