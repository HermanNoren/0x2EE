package view.panelstates;

import main.Game;
import view.HUD;
import view.drawers.SpriteDrawer;
import view.drawers.PlayerDrawer;
import view.drawers.TileDrawer;

import java.awt.*;
import java.util.ArrayList;

public class InGamePanelState implements PanelState{

    private Game game;
    private HUD hud;
    private ArrayList<SpriteDrawer> drawers;

    public InGamePanelState(Game game) {
        this.game = game;
        hud = new HUD(game.getPlayer());
        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer()));
        drawers.add(new TileDrawer(game.getTiles()));
    }

    @Override
    public void draw(Graphics2D g) {
        for (SpriteDrawer drawer : drawers) {
            drawer.draw(g);
        }
    }
}
