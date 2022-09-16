package view.panelstates;

import config.Config;
import controllers.KeyClickedController;
import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HighscorePanelState implements IPanelState {

    private ArrayList<String> scores;
    private ArrayList<IDrawer> drawers;
    private ArrayList<KeyListener> keyListeners;
    private Game game;

    private int rank, ypos;

    private Color gold = new Color(255, 221, 67);
    private Color silver = new Color(180, 215, 215);
    private Color bronze = new Color(106, 56, 5);


    private ArrayList<Color> rankColors = new ArrayList<>(
            Arrays.asList(gold, silver,
                    bronze, Color.white, Color.white));



    public HighscorePanelState() {
        this.game = Game.getInstance();
        keyListeners = new ArrayList<>();
        keyListeners.add(new KeyClickedController());
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(game.getBackButtons()));
        File file = new File("textfiles/highscores.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scores = new ArrayList<>();
        while(sc.hasNext()){
            scores.add(sc.next());
        }

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
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        FontMetrics metrics = g2.getFontMetrics();
        String paused = "HIGHSCORES";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

        ypos = 225;
        rank = 1;

        for (String score : scores){
            Color color = rankColors.get(rank - 1);
            String[] playerscore = score.split(":");
            String listitem = "#" + String.valueOf(rank) + " " + playerscore[0].toUpperCase() + ": " + playerscore[1];
            g2.setFont(new Font("Public Pixel", Font.PLAIN, 32-rank));
            g2.setColor(color);
            g2.drawString(listitem, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(listitem)) / 2 , ypos);

            ypos += 60;
            rank++;
        }


    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

}
