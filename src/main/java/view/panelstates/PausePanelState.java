package view.panelstates;

import config.Config;
import controllers.ButtonController;
import model.gameinterfaces.IGame;
import view.MainPanel;
import view.buttons.GameButton;
import controllers.buttonactions.MenuButtonAction;
import controllers.buttonactions.NewGameButtonAction;
import controllers.buttonactions.ResumeGameButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PausePanelState implements IPanelState {

    private final ButtonController bc;
    private final ArrayList<GameButton> buttons;
    private List<KeyListener> keyListeners;
    private List<IDrawer> drawers;
    private MainPanel mainPanel;

    public PausePanelState(MainPanel mainPanel, IGame game) {
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons(game);
        bc = new ButtonController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));
    }


    @Override
    public void draw(Graphics2D g2) {

        bc.update();
        g2.setColor(Color.black);
        g2.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.buttonFont);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        g2.setColor(Color.WHITE);
        g2.setFont(Config.titleFont);
        String paused = "PAUSED";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons(IGame game){
        GameButton pauseButton1 = new GameButton("RESUME", 325, 200, new ResumeGameButtonAction(EPanelState.INGAME, this, game));
        GameButton pauseButton2 = new GameButton("RESTART", 325, 300, new NewGameButtonAction(EPanelState.INGAME, this, game));
        GameButton pauseButton3 = new GameButton("MAIN MENU", 325, 400, new MenuButtonAction(EPanelState.MAINMENU, this));
        buttons.add(pauseButton1);
        buttons.add(pauseButton2);
        buttons.add(pauseButton3);
    }
}
