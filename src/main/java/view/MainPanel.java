package view;

import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainPanel extends JPanel implements IObserver {

    private final Game game;
    private IPanelState state;

    public MainPanel(Game game) {
        this.game = game;
        state = PanelStateFactory.createPanelState(EPanelState.INGAME, this, game);
        changeKeyListeners();
        setFocusable(true);
    }


    /**
     * See override description to see Graphics g draw implementation. This method
     * is used to draw all logic of the game.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        state.draw(g2);
    }


    public void changePanelState(EPanelState state) {
        this.state = PanelStateFactory.createPanelState(state, this, game);
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
