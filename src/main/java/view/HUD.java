package view;

import config.Config;
import gameobjects.Player;

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
    public void update(Graphics2D g2){
        g2.setFont(new Font("Public Pixel", Font.BOLD, 24));
        g2.drawString("" + player.getScore(), Config.SCREEN_WIDTH/2, 32);
        g2.drawString("$ " + player.getMoney(),Config.SCREEN_WIDTH - 220, Config.SCREEN_HEIGHT-80);
        g2.drawString("EXP: 500", Config.SCREEN_WIDTH - 220, Config.SCREEN_HEIGHT-50);
        Color mana = new Color(0, 102, 255);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.red);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 100, 200, 20, 0, 0);
        g2.setColor(Color.green);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 100, player.getHealth() * 2, 20, 0, 0);
        g2.setColor(Color.black);
        g2.drawRoundRect(20, Config.SCREEN_HEIGHT - 100, 200, 20, 0, 0);
        g2.setColor(mana);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 70, 200, 20, 0, 0);
        g2.setColor(Color.black);
        g2.drawRoundRect(20, Config.SCREEN_HEIGHT - 70, 200, 20, 0, 0);
    }
}
