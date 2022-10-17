package view.panelstates;

import controllers.ButtonSwitcherController;
import controllers.buttonactions.UpgradeArmorButton;
import model.helperclasses.TransactionHandler;
import view.IChangeableStatePanel;
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
    private final IChangeableStatePanel mainPanel;
    private TransactionHandler transactionHandler;
    private final ButtonSwitcherController stringButtonController;
    private final List<GameButton> buttons;
    private final List<GameButton> pictureButtons;
    private final List<KeyListener> keyListeners;
    private final List<IDrawer> drawers;

    Color panelColor = new Color(0, 0, 0);
    private final String headerText = "Shop";

    private final static String currentCostText =  "UPGRADE COSTS ";

    public ShopPanelState(IChangeableStatePanel mainPanel, TransactionHandler transactionHandler) {
        this.mainPanel = mainPanel;
        this.transactionHandler = transactionHandler;
        buttons = new ArrayList<>();
        keyListeners = new ArrayList<>();
        pictureButtons = new ArrayList<>();
        createShopButtons(transactionHandler);


        stringButtonController = new ButtonSwitcherController(buttons);
        keyListeners.add(stringButtonController);
        drawers = new ArrayList<>();
        drawers.add(new InShopDrawer(pictureButtons));
        drawers.add(new ButtonDrawer(buttons.get(2))); //only paint the leave-button
    }


    /**
     * draws everything on the shop panel
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2) {
        // draws the screen
        drawPanel(g2);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
    }

    private void drawPanel(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(Config.titleFont);
        g2.drawString(headerText, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(headerText)) / 2 , Config.SCREEN_HEIGHT/5);
        stringButtonController.update();
        g2.setColor(panelColor);
        g2.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        drawPlayerMoney(g2, transactionHandler.getMoney());

        drawWeaponCost(g2, transactionHandler.getWeaponUpgradeCost());
        drawWeaponUpgradePerks(g2, transactionHandler.getCurrentWeaponDamage(), transactionHandler.getUpgradedWeaponDamage());

        drawArmorCost(g2, transactionHandler.getArmorUpgradeCost());
        drawArmorUpgradePerks(g2, transactionHandler.getCurrentArmorReduction(), transactionHandler.ArmorReductionAfterUpgrade());

        g2.setFont(Config.buttonFont); //the font which the button are drawn in
    }

    /**
     * represents the money the player has, shown in the top right corner.
     * @param g2 is what is to be drawn
     * @param playerMoney is player money
     */
    private void drawPlayerMoney(Graphics2D g2, int playerMoney){
        g2.setColor(Color.WHITE);
        g2.setFont(Config.nameFont);
        g2.drawString((playerMoney + "$"),(Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(playerMoney + "$")), Config.SCREEN_HEIGHT/12);
    }

    /**
     * The current cost of the weapon to upgrade it.
     * @param g2 to be drawn
     * @param weaponUpgradeCost the cost, taken from transactionHandler.getWeaponUpgradeCost()
     */
    private void drawWeaponCost(Graphics2D g2, int weaponUpgradeCost){
        g2.setColor(Color.WHITE);
        g2.setFont(Config.inGameTextFont);
        String textToBeRepresented = currentCostText + weaponUpgradeCost +"$";
        g2.drawString((textToBeRepresented), Config.SCREEN_WIDTH/3, (int) ((Config.SCREEN_HEIGHT)/1.7));
    }

    /**
     * The current cost to upgrade the player's armor.
     * @param g2 Graphics2D drawing logic.
     * @param armorUpgradeCost The cost to upgrade the armor.
     */
    private void drawArmorCost(Graphics2D g2, int armorUpgradeCost){
        g2.setColor(Color.WHITE);
        g2.setFont(Config.inGameTextFont);
        String textToBeRepresented = currentCostText + armorUpgradeCost + "$";
        g2.drawString(textToBeRepresented, Config.SCREEN_WIDTH/3, (Config.SCREEN_HEIGHT)/4);
    }

    /**
     * Draws the current armor reduction and how much the armor reduction will increase
     * if it were to be updated.
     * @param g2 Graphics2D drawing logic.
     * @param currentDamageReduction The current armor reduction.
     * @param armorReductionAfterUpgrade the armor reduction if it were to be upgraded.
     */
    private void drawArmorUpgradePerks(Graphics2D g2, int currentDamageReduction, int armorReductionAfterUpgrade){
        g2.setColor(Color.WHITE);
        g2.setFont(Config.inGameTextFont);
        String currentDamageReductionText = "ARMOR %" + currentDamageReduction;
        String damageReductionAfterUpgradeText = " -> %" +  armorReductionAfterUpgrade;
        g2.drawString(currentDamageReductionText + damageReductionAfterUpgradeText,Config.SCREEN_WIDTH/3, Config.SCREEN_HEIGHT/3);
    }

    /**
     * Paints the current weapon damage and also how much damage the weapon will deal if it
     * were to be upgraded.
     * @param g2 Graphics2D drawing logic.
     * @param currentWeaponDamage Current damage done by the weapon.
     * @param weaponDamageAfterUpgrade Weapon damage if it were to be upgraded.
     */

    private void drawWeaponUpgradePerks(Graphics2D g2, int currentWeaponDamage, int weaponDamageAfterUpgrade){
        g2.setColor(Color.WHITE);
        g2.setFont(Config.inGameTextFont);
        String currentDamageText = "DAMAGE " + currentWeaponDamage;
        String damageAfterUpgradeText = " -> " + weaponDamageAfterUpgrade;
        g2.drawString(currentDamageText + damageAfterUpgradeText,Config.SCREEN_WIDTH/3, (int)(Config.SCREEN_HEIGHT/1.5));

    }

    /**
     * Creates a list of buttons, the first buttons are drawn in the InShopDrawer which draws pictures
     * representing buttons and the last button, the leave-button, is taken in at ButtonDrawer as a single
     * GameButton instance to be painted respectively.
     * @param transactionHandler The object which handles the logic behind the transactions of upgrades.
     */
    private void createShopButtons(TransactionHandler transactionHandler) {
        GameButton upgradeArmorButton = new GameButton("armor", Config.SCREEN_WIDTH / 10, Config.SCREEN_HEIGHT / 6, new UpgradeArmorButton(transactionHandler));
        GameButton upgradeWeaponButton = new GameButton("weapon", Config.SCREEN_WIDTH / 10, Config.SCREEN_HEIGHT / 2, new UpgradeWeaponAction(transactionHandler));
        GameButton leaveShopButton = new GameButton("LEAVE", Config.SCREEN_WIDTH / 3, (int) (Config.SCREEN_HEIGHT * 0.8), new MenuButtonAction(EPanelState.INGAME, this));
        buttons.add(upgradeArmorButton);
        buttons.add(upgradeWeaponButton);
        buttons.add(leaveShopButton);
        pictureButtons.add(upgradeWeaponButton);
        pictureButtons.add(upgradeArmorButton);

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
