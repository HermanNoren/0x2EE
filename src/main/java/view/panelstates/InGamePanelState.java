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

public class InGamePanelState implements IPanelState {

    private Game game;
    private HUD hud;
    private ArrayList<IDrawer> drawers;
    private final Camera camera;
    private final ArrayList<KeyListener> keyListeners;
    private MainPanel mainPanel;

    public InGamePanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game));
        keyListeners.add(new KeyClickedController(game));
        hud = new HUD(game.getPlayer());

        camera = Camera.getInstance();
        camera.setFocusedObject(game.getPlayer());
        keyListeners.add(new CameraController());

        drawers = new ArrayList<>();
        drawers.add(new MapDrawer(game.getGameMap()));
        drawers.add(new ProjectileDrawer(game.getProjectiles()));
        drawers.add(new PlayerDrawer(game.getPlayer()));
        drawers.add(new ShopDrawer(game.getshop()));
        drawers.add(new EnemyDrawer(game.getEnemies()));
    }


    @Override
    public void draw(Graphics2D g) {
        camera.update();
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }
        hud.update(g);
        if (game.getEscapePressed()){
            changePanelState(EPanelState.PAUSE);
            game.resetEscapePressed();
        }
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
