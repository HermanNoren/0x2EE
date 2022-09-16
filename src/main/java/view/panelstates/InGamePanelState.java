package view.panelstates;

import controllers.KeyClickedController;
import controllers.PlayerController;
import main.Game;
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

    private ArrayList<KeyListener> keyListeners;

    public InGamePanelState() {
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController());
        keyListeners.add(new KeyClickedController());
        hud = new HUD(game.getPlayer());
        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer()));
        drawers.add(new EnemyDrawer(game.getEnemies()));
        drawers.add(new TerrainDrawer(game.getTerrainBorder()));
    }


    @Override
    public void draw(Graphics2D g) {
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
