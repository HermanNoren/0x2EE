package view.panelstates;

import config.Config;
import controllers.ButtonController;
import controllers.HighscoreController;
import model.Game;
import view.MainPanel;
import view.buttons.GameButton;
import controllers.buttonactions.SaveScoreButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class NewHighscorePanelState implements IPanelState{

    private Game game;
    private final ButtonController bc;
    private final List<GameButton> buttons;
    private List<KeyListener> keyListeners;
    private List<IDrawer> drawers;

    private int xpos, ypos;
    private MainPanel mainPanel;


    public NewHighscorePanelState(MainPanel mainPanel, Game game){
        this.game = game;
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        keyListeners.add(new HighscoreController(game));
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));

    }

    @Override
    public void draw(Graphics2D g2) {

        bc.update();

        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.buttonFont);
        xpos = 100;
        ypos = 350;
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        g2.setColor(Color.white);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+= 100, ypos, 70, 2);
        g2.fillRect(xpos+= 100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);
        g2.fillRect(xpos+=100, ypos, 70, 2);

        xpos = 110;
        ypos = 340;
        g2.setFont(Config.nameFont);
        for (String letter : game.getHighscoreName()){
            g2.drawString(letter, xpos+=100, ypos);
        }
        g2.setColor(Color.white);
        g2.setFont(Config.titleFont);
        String title = "NEW HIGHSCORE";
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 128);
        g2.setFont(Config.infoFont);
        String action = "ENTER YOUR NAME:";
        g2.drawString(action, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(action)) / 2 , 220);
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        game.updateHighscoreList();
        mainPanel.changePanelState(panelState);
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
