package view.panelstates;

import config.Config;
import controllers.ButtonController;
import model.Game;
import view.MainPanel;
import view.buttons.GameButton;
import view.buttons.buttonactions.MenuButtonAction;
import view.buttons.buttonactions.QuitButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class MainMenuPanelState implements IPanelState {

    private final Game game;

    private final ButtonController bc;
    private final List<GameButton> buttons;
    private final List<IDrawer> drawers;
    private final List<KeyListener> keyListeners;
    private MainPanel mainPanel;

    public MainMenuPanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
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
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));
        for (IDrawer drawer : drawers) {
            drawer.draw(g2);
        }
        g2.setColor(Color.white);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "0x2EE";
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


    private void createButtons() {
        GameButton mainMenuButton1 = new GameButton("PLAY", 325, 200, new MenuButtonAction(EPanelState.INGAME, this));
        GameButton mainMenuButton2 = new GameButton("HIGHSCORES", 325, 300, new MenuButtonAction(EPanelState.HIGHSCORES, this));
        GameButton mainMenuButton3 = new GameButton("HOW TO PLAY", 325, 400, new MenuButtonAction(EPanelState.HOWTOPLAY, this));
        GameButton mainMenuButton4 = new GameButton("QUIT", 325, 500, new QuitButtonAction());
        buttons.add(mainMenuButton1);
        buttons.add(mainMenuButton2);
        buttons.add(mainMenuButton3);
        buttons.add(mainMenuButton4);
    }
}
