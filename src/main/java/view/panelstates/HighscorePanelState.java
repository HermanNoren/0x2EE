package view.panelstates;

import config.Config;
import controllers.ButtonController;
import model.Game;
import view.MainPanel;
import view.buttons.GameButton;
import view.buttons.buttonactions.MenuButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighscorePanelState implements IPanelState {

    private List<String> scores;
    private final ButtonController bc;
    private final ArrayList<GameButton> buttons;
    private List<IDrawer> drawers;
    private List<KeyListener> keyListeners;
    private Game game;
    private MainPanel mainPanel;

    private int rank, ypos;

    private Color gold = new Color(255, 221, 67);
    private Color silver = new Color(180, 215, 215);
    private Color bronze = new Color(131, 69, 7);


    private ArrayList<Color> rankColors = new ArrayList<>(
            Arrays.asList(gold, silver,
                    bronze, Color.white, Color.white));

    public HighscorePanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));
        scores = game.getHighScoreList();
    }

    @Override
    public void draw(Graphics2D g2) {

        bc.update();

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
            if (rank == 6){
                break;
            }else{
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

    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons(){
        GameButton backButton = new GameButton("BACK", 325, 575, new MenuButtonAction(EPanelState.MAINMENU, this));
        buttons.add(backButton);
    }
}
