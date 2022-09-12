package view;

import main.Game;
import controllers.KeyboardController;
import view.panelstates.InGamePanelState;
import view.panelstates.MainMenuPanelState;
import view.panelstates.PanelState;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel implements Observer {

    private Game game;
    private PanelState state;
    private KeyboardController keyboardController;

    public MainPanel(Game game, PanelState startState) {
        this.game = game;
        state = startState;
        keyboardController =  new KeyboardController(game);
        setFocusable(true);
        addKeyListener(keyboardController);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        checkForStateChange();
        state.draw(g2);
    }

    private void checkForStateChange() {
        if (game.readStateChangedFlag()) {
            game.resetStateChangedFlag();
            changePanelState();
        }
    }

    private void changePanelState() {
        switch (game.getStateTag()) {
            case "MainMenu" -> state = new MainMenuPanelState(game);
            case "InGame" -> state = new InGamePanelState(game);
        }
    }

    @Override
    public void draw() {
        repaint();
    }
}
