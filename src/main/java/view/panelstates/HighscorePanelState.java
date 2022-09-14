package view.panelstates;

import main.Game;
import view.MainPanel;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighscorePanelState implements  IPanelState {

    private ArrayList<String> scores;
    private MainPanel mainPanel;
    private ArrayList<IDrawer> drawers;

    private Game game;

    public HighscorePanelState(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.game = mainPanel.getGame();
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
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        g2.setColor(Color.black);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        FontMetrics metrics = g2.getFontMetrics();
        String paused = "HIGHSCORES";
        g2.drawString(paused, (mainPanel.getWidth() - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

        int ypos = 200;
        int rank = 1;
        for (String score : scores){
            String[] playerscore = score.split(":");
            String listitem = "#" + String.valueOf(rank) + " " + playerscore[0].toUpperCase() + ": " + playerscore[1];
            g2.setFont(new Font("Public Pixel", Font.PLAIN, 32));
            g2.drawString(listitem, (mainPanel.getWidth() - g2.getFontMetrics().stringWidth(listitem)) / 2 , ypos);
            ypos += 40;
            rank++;
        }


    }

}
