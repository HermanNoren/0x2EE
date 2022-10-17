package view.panelstates;

import config.Config;
import controllers.ButtonSwitcherController;
import model.gameinterfaces.ICreateGame;
import view.IChangeableStatePanel;
import view.buttons.GameButton;
import controllers.buttonactions.MenuButtonAction;
import controllers.buttonactions.NewGameButtonAction;
import controllers.buttonactions.QuitButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class MainMenuPanelState implements IPanelState {
    private final ButtonSwitcherController bc;
    private final List<GameButton> buttons;
    private final List<IDrawer> drawers;
    private final List<KeyListener> keyListeners;
    private IChangeableStatePanel mainPanel;

    private String title = "0x2EE";

    public MainMenuPanelState(IChangeableStatePanel mainPanel, ICreateGame game) {
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons(game);
        bc = new ButtonSwitcherController(buttons);
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
        g2.setFont(Config.BUTTON_FONT);
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setColor(Color.white);
        g2.setFont(Config.TITLE_FONT);
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 128);
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }


    private void createButtons(ICreateGame game) {
        GameButton mainMenuButton1 = new GameButton("PLAY", 325, 200, new NewGameButtonAction(EPanelState.INGAME, this, game));
        GameButton mainMenuButton2 = new GameButton("HIGHSCORES", 325, 300, new MenuButtonAction(EPanelState.HIGHSCORES, this));
        GameButton mainMenuButton3 = new GameButton("HOW TO PLAY", 325, 400, new MenuButtonAction(EPanelState.HOWTOPLAY, this));
        GameButton mainMenuButton4 = new GameButton("QUIT", 325, 500, new QuitButtonAction());
        buttons.add(mainMenuButton1);
        buttons.add(mainMenuButton2);
        buttons.add(mainMenuButton3);
        buttons.add(mainMenuButton4);
    }
}
