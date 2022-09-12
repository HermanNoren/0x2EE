package view.panelstates;

import main.Game;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.util.ArrayList;

public class MainMenuPanelState implements IPanelState {

    private Game game;
    private ArrayList<IDrawer> drawers;

    public MainMenuPanelState(Game game) {
        this.game = game;
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getMainMenuButtons()));
    }

    @Override
    public void draw(Graphics2D g) {
        for (IDrawer drawer : drawers) {
            drawer.draw(g);
        }
    }
}
