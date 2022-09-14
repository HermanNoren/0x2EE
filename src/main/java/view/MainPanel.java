package view;

import main.Game;
import controllers.KeyboardController;
import view.panelstates.InGamePanelState;
import view.panelstates.MainMenuPanelState;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel implements IObserver {

    private Game game;
    private IPanelState state;
    private KeyboardController keyboardController;

    public MainPanel(Game game) {
        this.game = game;
        state = PanelStateFactory.createPanelState(game.getStateTag(), this);
        keyboardController =  new KeyboardController(game);
        setFocusable(true);
        addKeyListener(keyboardController);
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
        removeKeyListener(keyboardController);
        addKeyListener(state.getKeyListener());
    }

    @Override
    public void draw() {
        repaint();
    }
}
