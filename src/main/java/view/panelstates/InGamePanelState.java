package view.panelstates;

import controllers.CameraController;
import controllers.KeyClickedController;
import controllers.PlayerController;
import model.Game;
import view.Camera;
import view.HUD;
import view.MainPanel;
import view.drawers.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InGamePanelState implements IPanelState {

    private Game game;
    private HUD hud;
    /**
     * Important to know in the constructor in which order the drawable objects are put in as
     * a drawing hierarchy is created (which object is drawn over whom).
     * The first object added to the Drawers ArrayList will be seen as under everything added after it.
     * */
    private List<IDrawer> drawers;
    private final Camera camera;
    private final ArrayList<KeyListener> keyListeners;
    private MainPanel mainPanel;

    public InGamePanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game));
        keyListeners.add(new KeyClickedController(game, this));
        hud = new HUD(game.getPlayer());

        camera = Camera.getInstance();
        camera.setFocusedObject(game.getPlayer());
        keyListeners.add(new CameraController());

        drawers = new ArrayList<>();
        drawers.add(new MapDrawer(game.getGameMap()));
        drawers.add(new ProjectileDrawer(game.getProjectiles()));
        drawers.add(new ShopDrawer(game.getshop(), game.getPlayer()));
        drawers.add(new PlayerDrawer(game.getPlayer()));
        drawers.add(new EnemyDrawer(game.getEnemies()));
        drawers.add(new ItemDrawer(game.getItems()));
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
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
