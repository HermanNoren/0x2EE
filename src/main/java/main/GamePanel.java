package main;

import controllers.KeyboardController;
import sprites.Sprite;
import worldclasses.Tile;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    private float x = 0;
    private float y = 0;
    private float xVel = 1f;
    private float yVel = 1f;

    private Game model;

    public GamePanel(Game model) {
        this.model = model;
        addKeyListener(new KeyboardController());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite sprite : model.getSprites()) {
            g.fillRect((int) sprite.getPos().x, (int) sprite.getPos().y, sprite.getRect().getWidth(), sprite.getRect().getHeight());
            //sprite.draw(g);
        }
    }
}
