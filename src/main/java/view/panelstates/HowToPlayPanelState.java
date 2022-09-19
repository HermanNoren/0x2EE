package view.panelstates;

import config.Config;
import controllers.KeyClickedController;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HowToPlayPanelState implements IPanelState{

    private Game game;

    private BufferedImage controls;

    private ArrayList<IDrawer> drawers;

    private ArrayList<KeyListener> keyListeners;

    public HowToPlayPanelState(){
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new KeyClickedController());
        controls = setImage("imgs/h2p.png");
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getBackButtons()));
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }

        g2.setColor(Color.white);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 48));
        String paused = "HOW TO PLAY";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 100);
        g2.drawImage(controls, 0,80, Config.SCREEN_WIDTH*100/150, Config.SCREEN_HEIGHT, null);
    }

    private BufferedImage setImage(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;

    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

}
