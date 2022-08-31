package main;

import controllers.KeyboardController;
import sprites.Sprite;
import worldclasses.Tile;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game model;

    public GamePanel(Game model) {
        this.model = model;
        addKeyListener(new KeyboardController());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Sprite sprite : model.getSprites()) {
            g.fillRect((int) sprite.getPos().x, (int) sprite.getPos().y, sprite.getRect().getWidth(), sprite.getRect().getHeight());
            //sprite.draw(g);
        }
    }
}
