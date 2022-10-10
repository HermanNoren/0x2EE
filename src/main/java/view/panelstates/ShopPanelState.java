package view.panelstates;

import controllers.ButtonController;
import controllers.buttonactions.UpgradeArmorButton;
import model.Game;
import model.ShopTransaction;
import view.MainPanel;
import view.buttons.GameButton;
import controllers.buttonactions.UpgradeWeaponAction;
import controllers.buttonactions.MenuButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;
import view.drawers.InShopDrawer;
import config.Config;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ShopPanelState implements IPanelState{
    private final MainPanel mainPanel;
    private final ButtonController stringButtonController;
    private final List<GameButton> buttons;
    private final List<GameButton> pictureButtons;
    private final List<KeyListener> keyListeners;
    private final List<IDrawer> drawers;

    Color panelColor = new Color(111, 78, 55);
    private final String headerText = "Shop";


    public ShopPanelState(MainPanel mainPanel, Game game) {
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        keyListeners = new ArrayList<>();
        pictureButtons = new ArrayList<>();
        createShopButtons(game.getShopTransaction());

        stringButtonController = new ButtonController(buttons);
        keyListeners.add(stringButtonController);
        drawers = new ArrayList<>();
        drawers.add(new InShopDrawer(pictureButtons));
        drawers.add(new ButtonDrawer(buttons.get(2))); //only paint the leave-button
    }



    /**
     * draws the background of the shop!
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(Config.titleFont);
        g2.drawString(headerText, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(headerText)) / 2 , Config.SCREEN_HEIGHT/5);
        stringButtonController.update();
        g2.setColor(panelColor);
        g2.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.buttonFont);


        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
    }


    private void createShopButtons(ShopTransaction shopTransaction) {
        GameButton upgradeArmorButton = new GameButton("armor", Config.SCREEN_WIDTH / 8, Config.SCREEN_HEIGHT / 6, new UpgradeArmorButton(shopTransaction));
        GameButton upgradeWeaponButton = new GameButton("weapon", Config.SCREEN_WIDTH / 8, Config.SCREEN_HEIGHT / 2, new UpgradeWeaponAction(shopTransaction));
        GameButton leaveShopButton = new GameButton("LEAVE", Config.SCREEN_WIDTH / 3, (int) (Config.SCREEN_HEIGHT * 0.8), new MenuButtonAction(EPanelState.INGAME, this));
        pictureButtons.add(upgradeWeaponButton);
        pictureButtons.add(upgradeArmorButton);
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
