package view.panelstates;

import main.Game;
import view.HUD;
import view.drawers.Drawer;
import view.drawers.PlayerDrawer;

import java.awt.*;
import java.util.ArrayList;

public class InGamePanel implements PanelState{

    private Game game;
    private HUD hud;

    private ArrayList<Drawer> drawers;

    PlayerDrawer pd;

    public InGamePanel(Game game) {
        this.game = game;
        try{
            hud = new HUD(game.getPlayer());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        drawers = new ArrayList<>();
        drawers.add(new PlayerDrawer(game.getPlayer()));
    }

    @Override
    public void draw(Graphics2D g) {
        for (Drawer drawer : drawers) {
            drawer.draw(g);
        }
    }
}
