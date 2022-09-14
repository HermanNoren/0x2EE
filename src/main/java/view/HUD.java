package view;

import sprites.Player;

import javax.swing.*;
import java.awt.*;

public class HUD {

    private Player player;

    public HUD(Player player){
        this.player = player;
    }

    /**
     * Updates the HUD of the game (Player health, score & money)
     * @param g2
     */
    public void update(Graphics2D g2, JPanel panel){
        g2.setFont(new Font("Public Pixel", Font.BOLD, 24));
        g2.drawString("" + player.getScore(), panel.getWidth()/2, 32);
        g2.drawString("$ " + player.getMoney(),panel.getWidth() - 240, panel.getHeight()-70);
        g2.drawString("EXP: 500",panel.getWidth() - 240, panel.getHeight()-40);
        Color mana = new Color(0, 102, 255);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.red);
        g2.fillRoundRect(20, panel.getHeight() - 70, 200, 20, 0, 0);
        g2.setColor(Color.green);
        g2.fillRoundRect(20, panel.getHeight() - 70, player.getHealth() * 2, 20, 0, 0);
        g2.setColor(Color.black);
        g2.drawRoundRect(20, panel.getHeight() - 70, 200, 20, 0, 0);
        g2.setColor(mana);
        g2.fillRoundRect(20, panel.getHeight() - 40, 200, 20, 0, 0);
        g2.setColor(Color.black);
        g2.drawRoundRect(20, panel.getHeight() - 40, 200, 20, 0, 0);
    }
}
