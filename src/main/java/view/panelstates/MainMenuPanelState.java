package view.panelstates;

import config.Config;
import controllers.ButtonController;
import controllers.KeyClickedController;
import model.Game;
import model.gamestates.HighscoreState;
import model.gamestates.HowToPlayState;
import model.gamestates.InGameState;
import view.MainPanel;
import view.buttons.GameButton;
import view.buttons.buttonactions.MenuButtonAction;
import view.buttons.buttonactions.QuitButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainMenuPanelState implements IPanelState {

    private final Game game;

    private final ButtonController bc;
    private final ArrayList<GameButton> buttons;
    private final ArrayList<IDrawer> drawers;
    private final ArrayList<KeyListener> keyListeners;

    public MainMenuPanelState() {
        this.game = Game.getInstance();
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
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 12));

        g2.setColor(Color.white);
        g2.setFont(new Font("Public Pixel", Font.PLAIN, 64));
        String paused = "0x2EE";
        g2.drawString(paused, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(paused)) / 2 , 128);
    }

    @Override
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons() {
        GameButton mainMenuButton1 = new GameButton("PLAY", 325, 200, new MenuButtonAction(new InGameState()));
        GameButton mainMenuButton2 = new GameButton("HIGHSCORES", 325, 300, new MenuButtonAction(new HighscoreState()));
        GameButton mainMenuButton3 = new GameButton("HOW TO PLAY", 325, 400, new MenuButtonAction(new HowToPlayState()));
        GameButton mainMenuButton4 = new GameButton("QUIT", 325, 500, new QuitButtonAction());
        buttons.add(mainMenuButton1);
        buttons.add(mainMenuButton2);
        buttons.add(mainMenuButton3);
        buttons.add(mainMenuButton4);
    }
}
