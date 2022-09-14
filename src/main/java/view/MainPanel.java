package view;

import main.Game;
import controllers.KeyClickedController;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainPanel extends JPanel implements IObserver {

    private Game game;
    private IPanelState state;

    public MainPanel(Game game) {
        this.game = game;
        state = PanelStateFactory.createPanelState(game.getStateTag(), this);
        changeKeyListeners();
        setFocusable(true);
    }

    public Game getGame() {
        return game;
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
        changeKeyListeners();
    }

    private void changeKeyListeners() {
        for (KeyListener listener : getKeyListeners()) {
            removeKeyListener(listener);
        }
        for (KeyListener listener : state.getKeyListeners()) {
            addKeyListener(listener);
        }
    }

    @Override
    public void draw() {
        repaint();
    }
}
