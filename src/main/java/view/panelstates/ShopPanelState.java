package view.panelstates;

import model.Game;
import view.MainPanel;
import view.buttons.GameButton;
import view.buttons.buttonactions.MenuButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;
import view.drawers.InShopDrawer;
import config.Config;
import controllers.ButtonController;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ShopPanelState implements IPanelState{
    private final Game game;
    private final MainPanel mainPanel;
    private final ButtonController bc;
    private final ArrayList<GameButton> buttons;
    private List<KeyListener> keyListeners;
    private List<IDrawer> drawers;
    private final String headerText = "Shop";


    public ShopPanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));
        drawers.add(new InShopDrawer(game.getPlayer().getMoney()));
    }

    @Override
    public void draw(Graphics2D g2) {
        bc.update();
        g2.setColor(new Color(153,102,0));
        g2.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.buttonFont);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        g2.setColor(Color.WHITE);
        g2.setFont(Config.titleFont);
        g2.drawString(headerText, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(headerText)) / 2 , Config.SCREEN_HEIGHT/5);

    }
    private void createButtons(){
        GameButton upgradeArmorButton = new GameButton("UPGRADE ARMOR", 325, 200,
                new MenuButtonAction(EPanelState.SHOP, this));
        GameButton upgradeWeaponButton = new GameButton("UPGRADE WEAPON", 325, 300,
                new MenuButtonAction(EPanelState.SHOP, this));
        GameButton leaveShopButton = new GameButton("LEAVE", 325, 400,
                new MenuButtonAction(EPanelState.INGAME, this));
        buttons.add(upgradeArmorButton);
        buttons.add(upgradeWeaponButton);
        buttons.add(leaveShopButton);
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
