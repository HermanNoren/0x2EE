package view.panelstates;

import config.Config;
import controllers.HighscoreController;
import model.Game;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class NewHighscorePanelState implements IPanelState{

    private Game game;
    private ArrayList<KeyListener> keyListeners;
    private ArrayList<IDrawer> drawers;

    private int xpos, ypos;

    public NewHighscorePanelState(){
        this.game = Game.getInstance();
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getBackButtons()));
        keyListeners = new ArrayList<>();
        keyListeners.add(new HighscoreController());

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        xpos = 100;
        ypos = 350;
        g2.setColor(Color.white);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+= 100, ypos, 70, 2);
        g2.fillRect(xpos+= 100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);

        xpos = 110;
        ypos = 340;
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 56));
        for (String letter : game.getHighscoreName()){
            g2.drawString(letter, xpos+=100, ypos);
        }
        g2.setColor(Color.white);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String title = "NEW HIGHSCORE";
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 128);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 32));
        String action = "ENTER YOUR NAME:";
        g2.drawString(action, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(action)) / 2 , 220);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
