package view.panelstates;

import config.Config;
import controllers.ButtonSwitcherController;
import controllers.HighscoreController;
import model.gameinterfaces.IHasHighscore;
import view.IChangeableStatePanel;
import view.buttons.GameButton;
import controllers.buttonactions.SaveScoreButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class NewHighscorePanelState implements IPanelState{
    private IHasHighscore game;
    private final ButtonSwitcherController bc;
    private final List<GameButton> buttons;
    private List<KeyListener> keyListeners;
    private List<IDrawer> drawers;

    private int xpos, ypos;
    private IChangeableStatePanel mainPanel;


    public NewHighscorePanelState(IChangeableStatePanel mainPanel, IHasHighscore game){
        this.game = game;
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonSwitcherController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        keyListeners.add(new HighscoreController(game));
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));

    }

    private void drawTitles(Graphics2D g2){
        g2.setFont(Config.TITLE_FONT);
        String title = "NEW HIGHSCORE";
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 128);
        g2.setFont(Config.INFO_FONT);
        String action = "ENTER YOUR NAME:";
        g2.drawString(action, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(action)) / 2 , 220);
    }

    private void drawNameLetters(Graphics2D g2){
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
        g2.setFont(Config.NAME_FONT);
        for (String letter : game.getHighscoreName()){
            g2.drawString(letter, xpos+=100, ypos);
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        bc.update();

        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setColor(Color.white);
        drawTitles(g2);
        drawNameLetters(g2);
        g2.setFont(Config.BUTTON_FONT);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        if (game.getHighscoreName().size() == 6) {
            game.updateHighscoreList();
            mainPanel.changePanelState(panelState);
        }
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons(){
        GameButton button = new GameButton("CONTINUE", 325, 575, new SaveScoreButtonAction(EPanelState.MAINMENU, this));
        buttons.add(button);
    }
}
