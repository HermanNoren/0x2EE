package view;

import config.Config;
import model.gameobjects.Player;

import java.awt.*;

public class HUD {

    private Player player;

    private Color goldColor = new Color(255,221,67);
    private Color manaColor = new Color(0,102,255);
    private BasicStroke outline = new BasicStroke(3);


    public HUD(Player player){
        this.player = player;

    }

    /**
     * Updates the HUD of the game (Player health, score & money)
     * @param g2
     */
   public void update(Graphics2D g2){
        g2.setColor(Color.black);
        g2.setFont(Config.IN_GAME_TEXT_FONT);
        g2.drawString("" + player.getScore(), Config.SCREEN_WIDTH/2, 32);
        g2.drawImage(ImageHandler.getImage("imgs/coin/0.png"), Config.SCREEN_WIDTH - 150, Config.SCREEN_HEIGHT-80, 30, 30, null);
        g2.setColor(goldColor);
        g2.drawString("" + player.getMoney(),Config.SCREEN_WIDTH - 100, Config.SCREEN_HEIGHT-55);
        g2.setColor(Color.red);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 100, 200, 20, 0, 0);
        g2.setColor(Color.green);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 100, (int) (200 - (200 * (1 - player.getHealth() / player.getMaxHp()))), 20, 0, 0);
        g2.setColor(Color.black);
        g2.setStroke(outline);
        g2.drawRoundRect(20, Config.SCREEN_HEIGHT - 100, 200, 20, 0, 0);

        //Mana has not yet been implemented
        g2.setColor(manaColor);
        g2.fillRoundRect(20, Config.SCREEN_HEIGHT - 70, 200, 20, 0, 0);
        g2.setColor(Color.black);
        g2.drawRoundRect(20, Config.SCREEN_HEIGHT - 70, 200, 20, 0, 0);
    }

}
