package model.helperclasses;


import model.gameobjects.IHasGear;
import model.upgradables.IUpgradable;

/**
 * The class which handles the logic behind the shop transactions.
 * Interfaces are used to maximize abstraction and manipulate
 * them through parametric polymorphism.
 */
public class TransactionHandler {
    private final IHasGear player;
    private final IUpgradable armor, weapon;

    public TransactionHandler(IHasGear player){
        this.player = player;
        this.armor =  player.getArmor();
        this.weapon = player.getWeapon();
    }


    /**
     * Sees if a purchase is possible.
     * @param currentPrize The current price of the Upgradable.
     * @return Boolean if the player can afford the upgrade.
     */
    private boolean purchasePossible(int currentPrize){
        return ((player.getMoney() - currentPrize) >= 0);
    }

    /**
     * Gets the player money int.
     * @return the amount of money the player has.
     */
    public int getMoney(){
        return player.getMoney();
    }

    /**
     * @return the cost to upgrade the weapon.
     */

    public int getWeaponUpgradeCost(){
        return weapon.upgradeCost();
    }

    /**
     * @return the current damage the weapon deals.
     */
    public int getCurrentWeaponDamage(){
        return weapon.currentStats();
    }

    /**
     * @return the damage the weapon deals if it were to be upgraded.
     */
    public int weaponDamageAfterUpgrade(){
        return weapon.statsIfUpgraded();
    }

    /**
     * Method which takes the interface of the Upgradable thing in question.
     * The method implements this upgrade in the method "upgradeArmor" (below).
     * @param toBeUpgraded The thing to be upgraded.
     */
    private void upgrade(IUpgradable toBeUpgraded){
        newPlayerMoneyAmount(player.getMoney() - toBeUpgraded.upgradeCost());
        toBeUpgraded.upgrade();
    }

    /**
     * Upgrades armor if possible.
     */
    public void upgradeArmor(){
        if(purchasePossible(armor.upgradeCost())) upgrade(armor);
    }

    /**
     * Upgrades weapon, if possible.
     */
    public void upgradeWeapon(){
        if(purchasePossible(weapon.upgradeCost())) upgrade(player.getWeapon());
    }

    /**
     * Inspected by the view to see the amount of armor reduction after the upgrade.
     * @return What the armor reduction will be after an upgrade.
     */
    public int armorReductionAfterUpgrade(){
        return armor.statsIfUpgraded();
    }

    /**
     * Viewed by the view to see how large the current
     * armor reduction is.
     * @return The current armor reduction.
     */
    public int getCurrentArmorReduction(){
        return armor.currentStats();
    }

    /**
     * Used by the view to see the cost of upgrading the weapon.
     * @return The cost to upgrade the weapon.
     */
    public int getArmorUpgradeCost(){
        return armor.upgradeCost();
    }

    /**
     * Sets the new amount of money the player has after a transaction.
     * @param amountAfterTransaction is taken as an argument in player.setMoney, which will be
     *                               the new amount of money for the player.
     */
    private void newPlayerMoneyAmount(int amountAfterTransaction) {
        player.setMoney(amountAfterTransaction);
    }
}
