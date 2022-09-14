package view;

import controllers.PlayerController;
import main.Game;
import controllers.KeyboardController;
import view.panelstates.InGamePanelState;
import view.panelstates.MainMenuPanelState;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainPanel extends JPanel implements IObserver {

    private IPanelState state;
    private final KeyboardController keyboardController;
    private final Game game = Game.getInstance();
    private KeyListener keyListener;
    public MainPanel() {
        state = PanelStateFactory.createPanelState(game.getStateTag(), this);
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
        state = PanelStateFactory.createPanelState(game.getStateTag(), this);
        removeKeyListener(keyboardController);
        addKeyListener(new PlayerController());
    }

    @Override
    public void draw() {
        repaint();
    }
}
