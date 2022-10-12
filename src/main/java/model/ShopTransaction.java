package model;

import model.armor.Armor;
import model.gameobjects.IUpgradable;
import model.gameobjects.Player;
import model.weapons.Weapon;

public class ShopTransaction {
    private final Player player;
    private final Weapon weapon;
    private final Armor armor;


    public ShopTransaction(Player player){
        this.player = player;
        this.weapon = player.getWeapon();
        this.armor = player.getArmor();
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
        return weapon.damage;
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
        if(purchasePossible(weapon.upgradeCost())) upgrade(weapon);
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
        return armor.currentDamageReduction();
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
