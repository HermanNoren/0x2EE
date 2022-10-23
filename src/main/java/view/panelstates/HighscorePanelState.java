package view.panelstates;

import config.Config;
import controllers.ButtonSwitcherController;
import model.helperclasses.HighscoreHandler;
import view.IChangeableStatePanel;
import view.buttons.GameButton;
import controllers.buttonactions.MenuButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles the view for the highscore state.
 * @author Rickard Leksell
 */
class HighscorePanelState implements IPanelState {

    private List<String> scores;
    private final ButtonSwitcherController bc;
    private final List<GameButton> buttons;
    private List<IDrawer> drawers;
    private List<KeyListener> keyListeners;
    private IChangeableStatePanel mainPanel;

    private String title = "HIGHSCORES";

    private int rank, ypos;

    private Color gold = new Color(255, 221, 67);
    private Color silver = new Color(180, 215, 215);
    private Color bronze = new Color(131, 69, 7);


    private List<Color> rankColors = new ArrayList<>(
            Arrays.asList(gold, silver,
                    bronze, Color.white, Color.white));

    private HighscoreHandler highscoreHandler;

    public HighscorePanelState(IChangeableStatePanel mainPanel) {
        this.mainPanel = mainPanel;
        this.highscoreHandler = new HighscoreHandler();
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonSwitcherController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));

        scores = highscoreHandler.getHighscoreList();

    }

    private void drawScores(Graphics2D g2){
        ypos = 225;
        rank = 1;
        for (String score : scores){
            if (rank == 6){
                break;
            }else{
                Color color = rankColors.get(rank - 1);
                String[] playerscore = score.split(":");
                String listitem = "#" + rank + " " + playerscore[0].toUpperCase() + ": " + playerscore[1];
                g2.setFont(new Font("Public Pixel", Font.PLAIN, 32-rank));
                g2.setColor(color);
                g2.drawString(listitem, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(listitem)) / 2 , ypos);
                ypos += 60;
                rank++;
            }

        }
    }

    @Override
    public void draw(Graphics2D g2) {

        bc.update();
        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setColor(Color.white);
        g2.setFont(Config.TITLE_FONT);
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 128);
        drawScores(g2);

        g2.setFont(Config.BUTTON_FONT);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
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
