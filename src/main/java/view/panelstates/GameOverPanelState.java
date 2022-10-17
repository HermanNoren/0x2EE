package view.panelstates;

import config.Config;
import controllers.ButtonSwitcherController;
import controllers.buttonactions.NewGameButtonAction;
import model.gameinterfaces.ICreateGame;
import view.IChangeableStatePanel;
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

    private IChangeableStatePanel mainPanel;
    private final ButtonSwitcherController bc;
    private final List<GameButton> buttons;
    private final List<IDrawer> drawers;
    private final List<KeyListener> keyListeners;

    private String title = "GAME OVER";


    public GameOverPanelState(IChangeableStatePanel mainPanel, ICreateGame game){
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
        GameButton button1 = new GameButton("RESTART", 325, 200, new NewGameButtonAction(EPanelState.INGAME, this, game));
        GameButton button2 = new GameButton("MAIN MENU", 325, 300, new MenuButtonAction(EPanelState.MAINMENU, this));
        GameButton button3 = new GameButton("QUIT", 325, 400, new QuitButtonAction());
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }
}
