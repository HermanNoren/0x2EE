package view.panelstates;

import controllers.KeyClickedController;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class MainMenuPanelState implements IPanelState {

    private Game game;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    private ArrayList<KeyListener> keyListeners;

    public MainMenuPanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
        keyListeners = new ArrayList<>();
        keyListeners.add(new KeyClickedController(game));
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getMainMenuButtons()));
    }

    @Override
    public void draw(Graphics2D g2) {
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));

        g2.setColor(Color.black);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "0x2EE";
        g2.drawString(paused, (mainPanel.getWidth() - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
