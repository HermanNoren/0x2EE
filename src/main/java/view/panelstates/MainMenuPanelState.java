package view.panelstates;

import config.Config;
import controllers.KeyClickedController;
import model.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainMenuPanelState implements IPanelState {

    private Game game;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    private ArrayList<KeyListener> keyListeners;

    private BufferedImage startImg;

    public MainMenuPanelState() {
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new KeyClickedController());
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getMainMenuButtons()));
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));

        g2.setColor(Color.white);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "0x2EE";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);


    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
