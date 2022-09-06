package view.panelstates;

import main.Game;
import sprites.Sprite;
import view.HUD;
import view.MainPanel;

import java.awt.*;

public class InGamePanel implements PanelState{

    private Game game;
    private HUD hud;

    public InGamePanel(Game game) {
        this.game = game;
        try{
            hud = new HUD(game.getPlayer());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        for (Sprite sprite : game.getSprites()) {
            sprite.draw(g2);
        }
    }
}
