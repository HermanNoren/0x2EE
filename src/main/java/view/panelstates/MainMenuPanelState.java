package view.panelstates;

import controllers.KeyboardController;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainMenuPanelState implements IPanelState {

    private Game game;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    private KeyListener keyListener;

    public MainMenuPanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
        this.keyListener = new KeyboardController(game);
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getMainMenuButtons()));
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setColor(Color.black);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "0x2EE";
        g2.drawString(paused, (mainPanel.getWidth() - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }

    @Override
    public KeyListener getKeyListener() {
        return keyListener;
    }
}
