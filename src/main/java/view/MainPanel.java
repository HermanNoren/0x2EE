package view;

import model.Game;
import model.mapclasses.panelstates.EPanelState;
import model.mapclasses.panelstates.IPanelState;
import model.mapclasses.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainPanel extends JPanel implements IObserver {

    private final Game game;
    private IPanelState state;

    public MainPanel(Game game) {
        this.game = game;
        state = PanelStateFactory.createPanelState(EPanelState.MAINMENU, this);
        changeKeyListeners();
        setFocusable(true);
    }

    public Game getGame() {
        return game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        state.draw(g2);
    }


    public void changePanelState(EPanelState state) {
        this.state = PanelStateFactory.createPanelState(state, this);
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
