package view.panelstates;

import config.Config;
import controllers.ButtonController;
import controllers.buttonactions.NewGameButtonAction;
import model.gameinterfaces.INewGamable;
import view.MainPanel;
import view.buttons.GameButton;
import controllers.buttonactions.MenuButtonAction;
import controllers.buttonactions.QuitButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameOverPanelState implements IPanelState{

    private MainPanel mainPanel;
    private final ButtonController bc;
    private final List<GameButton> buttons;
    private final List<IDrawer> drawers;
    private final List<KeyListener> keyListeners;


    public GameOverPanelState(MainPanel mainPanel, INewGamable game){
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
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.buttonFont);
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setColor(Color.white);
        g2.setFont(Config.titleFont);
        String paused = "GAME OVER";
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

    private void createButtons(INewGamable game) {
        GameButton button1 = new GameButton("RESTART", 325, 200, new NewGameButtonAction(EPanelState.INGAME, this, game));
        GameButton button2 = new GameButton("MAIN MENU", 325, 300, new MenuButtonAction(EPanelState.MAINMENU, this));
        GameButton button3 = new GameButton("QUIT", 325, 400, new QuitButtonAction());
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }
}
