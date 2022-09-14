package view.panelstates;

import com.sun.tools.javac.Main;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PausePanelState implements IPanelState {

    private MainPanel mainPanel;
    private Game game;
    private ArrayList<IDrawer> drawers;

    public PausePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
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
        g2.drawString(paused, (mainPanel.getWidth() - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }



}
