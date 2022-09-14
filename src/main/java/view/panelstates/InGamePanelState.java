package view.panelstates;

import main.Game;
import view.HUD;
import view.MainPanel;
import view.drawers.IDrawer;
import view.drawers.PlayerDrawer;
import view.drawers.TileDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InGamePanelState implements IPanelState {

    private Game game;
    private HUD hud;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    public InGamePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
        hud = new HUD(game.getPlayer());
        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer()));
        drawers.add(new TileDrawer(game.getTiles()));
    }

    @Override
    public void draw(Graphics2D g) {
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }
        hud.update(g, mainPanel);
    }
}
