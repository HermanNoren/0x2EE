package view.panelstates;

import config.Config;
import controllers.KeyClickedController;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PausePanelState implements IPanelState {

    private Game game;
    private ArrayList<KeyListener> keyListeners;
    private ArrayList<IDrawer> drawers;

    public PausePanelState() {
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new KeyClickedController());
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getPauseButtons()));

    }


    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }

        g2.setColor(Color.black);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "PAUSED";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

}
