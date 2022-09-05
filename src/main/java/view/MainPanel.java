package view;

import controllers.KeyboardController;
import main.Game;
import sprites.Player;
import sprites.Sprite;
import view.panelstates.PanelState;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel implements Observer{

    private Game game;
    private PanelState state;
    private KeyboardController keyboardController;

    public MainPanel(Game game, PanelState startState) {
        this.game = game;
        state = startState;
        keyboardController =  new KeyboardController(game);
        addKeyListener(keyboardController);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.Draw(g);
    }

    @Override
    public void update() {
        repaint();
    }
}
