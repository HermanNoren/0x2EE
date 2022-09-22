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

public class PausePanelState implements IPanelState {

    private Game game;
    private final ButtonController bc;
    private final ArrayList<GameButton> buttons;
    private ArrayList<KeyListener> keyListeners;
    private ArrayList<IDrawer> drawers;
    private MainPanel mainPanel;

    public PausePanelState(MainPanel mainPanel) {
        this.game = Game.getInstance();
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
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "PAUSED";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);

    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons(){
        GameButton pauseButton1 = new GameButton("RESUME", 325, 200, new MenuButtonAction(EPanelState.INGAME, this));
        GameButton pauseButton2 = new GameButton("RESTART", 325, 300, new MenuButtonAction(EPanelState.INGAME, this));
        GameButton pauseButton3 = new GameButton("MAIN MENU", 325, 400, new MenuButtonAction(EPanelState.MAINMENU, this));
        buttons.add(pauseButton1);
        buttons.add(pauseButton2);
        buttons.add(pauseButton3);
    }
}
