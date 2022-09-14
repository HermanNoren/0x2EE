package view.drawers;

import buttons.GameButton;

import java.awt.*;
import java.util.ArrayList;

public class ButtonDrawer implements IDrawer {
    private ArrayList<GameButton> buttons;

    public ButtonDrawer(ArrayList<GameButton> buttons) {
        this.buttons = buttons;
    }

    @Override
    public void draw(Graphics2D g) {
        for (GameButton button : buttons) {
            fillButtonArea(g, button);
            drawOutline(g, button);
            writeButtonText(g, button);
        }
    }

    private static void writeButtonText(Graphics2D g, GameButton button) {
        g.setColor(Color.BLACK);
        Font oldFont = g.getFont();
        Font newFont = oldFont.deriveFont(oldFont.getSize() * 2F);
        g.setFont(newFont);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(button.getButtonText(), (int) button.getPos().x + (button.getWidth() - metrics.stringWidth(button.getButtonText())) / 2,
                (int) button.getPos().y + (button.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent());
        g.setFont(oldFont);
    }

    private static void drawOutline(Graphics2D g, GameButton button) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.drawRect((int) button.getPos().x, (int) button.getPos().y, button.getWidth(), button.getHeight());
    }

    private static void fillButtonArea(Graphics2D g, GameButton button) {
        if (button.getIsSelected()) {
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillRect((int) button.getPos().x, (int) button.getPos().y, button.getWidth(), button.getHeight());
    }
}