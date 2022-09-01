package view;

import controllers.KeyboardController;
import main.Game;
import sprites.Sprite;
import view.panelstates.PanelState;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel implements Observer{

    private Game game;
    private PanelState state;
    private KeyboardController keyboardController;

    public MainPanel(Game model) {
        this.game = game;
        keyboardController =  new KeyboardController(game);
        addKeyListener(keyboardController);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite sprite : game.getSprites()) {
            g.fillRect((int) sprite.getPos().x, (int) sprite.getPos().y, sprite.getWidth(), sprite.getHeight());
            //sprite.draw(g);
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
