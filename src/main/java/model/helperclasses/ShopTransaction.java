package model.helperclasses;


import model.gameobjects.IPlayer;
import model.gameobjects.IUpgradable;


public class ShopTransaction {
    private final IPlayer player;
    private final IUpgradable armor;
    private final IUpgradable weapon;



    public ShopTransaction(IPlayer player){
        this.player = player;
        this.armor =  player.getArmor();
        this.weapon = player.getWeapon();

    }


    public boolean purchasePossible(int currentPrize){
        return ((player.getMoney() - currentPrize) >= 0);
    }

    public int getMoney(){
        return player.getMoney();
    }

    public int getWeaponUpgradeCost(){
        return weapon.upgradeCost();
    }

    public int getCurrentWeaponDamage(){
        return weapon.currentStat();
    }
    public int getUpgradedWeaponDamage(){
        return weapon.statsIfUpgraded();
    }

    /**
     * Method which takes the interface of the upgradable thing in question.
     * The method implements this upgrade in the method "upgradeArmor" (below).
     * @param toBeUpgraded The thing to be upgraded.
     */
    public void upgrade(IUpgradable toBeUpgraded){
        newPlayerMoneyAmount(player.getMoney() - toBeUpgraded.upgradeCost());
        toBeUpgraded.levelUp();
    }

    /**
     * Checks if the player has enough money, will upgrade armor if enough.
     */
    public void upgradeArmor(){
        if(purchasePossible(armor.upgradeCost())) upgrade(armor);
    }
    public void upgradeWeapon(){
        if(purchasePossible(weapon.upgradeCost())) upgrade(player.getWeapon());
    }

    /**
     * Inspected by the view to see the amount of armor reduction after the upgrade.
     * @return What the armor reduction will be after an upgrade.
     */
    public int ArmorReductionAfterUpgrade(){
        return armor.statsIfUpgraded();
    }

    /**
     * Viewed by the view to see how large the current
     * armor reduction is.
     * @return The current armor reduction.
     */
    public int CurrentArmorReduction(){
        return armor.currentStat();
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
