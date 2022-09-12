package view.panelstates;

import main.Game;
import view.HUD;
import view.drawers.ButtonDrawer;
import view.drawers.PlayerDrawer;
import view.drawers.SpriteDrawer;
import view.drawers.TileDrawer;

import java.awt.*;
import java.util.ArrayList;

public class MainMenuPanelState implements PanelState {

    private Game game;
    private ArrayList<SpriteDrawer> drawers;

    public MainMenuPanelState(Game game) {
        this.game = game;
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getMainMenuButtons()));
    }

    @Override
    public void draw(Graphics2D g) {
        for (SpriteDrawer drawer : drawers) {
            drawer.draw(g);
        }
    }
}
