package sprites.buttons;

import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.Sprite;
import config.Config;
import sprites.buttons.buttonactions.ButtonAction;

import java.awt.*;
import java.io.IOException;

public class GameButton implements Sprite {

    private String buttonText;
    private Vector2 pos;
    private int width = Config.SPRITE_SIZE * 18;
    private int height = Config.SPRITE_SIZE * 4;
    private ButtonAction action;

    private boolean isSelected;

    public GameButton(String buttonText, int x, int y, ButtonAction action) {
        this.buttonText = buttonText;
        pos = new Vector2(x, y);
        isSelected = false;
        this.action = action;
    }

    public void isClicked() {
        action.performAction();
    }

    public void setIsSelected(boolean value) {
        isSelected = value;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.drawRect((int) pos.x, (int) pos.y, width, height);
        if (isSelected) {
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillRect((int) pos.x, (int) pos.y, width, height);
        g.setColor(Color.BLACK);
        Font oldFont = g.getFont();
        Font newFont = oldFont.deriveFont(oldFont.getSize() * 2F);
        g.setFont(newFont);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(buttonText, (int) pos.x + (width - metrics.stringWidth(buttonText)) / 2,
                (int) pos.y + (height - metrics.getHeight()) / 2 + metrics.getAscent());
        g.setFont(oldFont);
    }
}
