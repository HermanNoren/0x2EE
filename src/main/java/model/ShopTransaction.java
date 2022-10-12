package model;

import model.armor.Armor;
import model.gameobjects.IUpgradable;
import model.gameobjects.Player;
import model.weapons.Weapon;

public class ShopTransaction {

    private int currentWeaponPrize = 10;

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


    public void upgradeWeapon(){
       if(purchasePossible(currentWeaponPrize)){
           currentWeaponPrize *= weapon.currentLevel();
           newPlayerMoneyAmount(player.getMoney() - currentWeaponPrize);
           weapon.levelUp();
       }
    }
    public int getWeaponUpgradeCost(){
        return currentWeaponPrize * weapon.currentLevel();
    }

    public int getCurrentWeaponPrize(){
        return currentWeaponPrize;
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

    /**
     * Inspected by the view to see the amount of armor reduction after the upgrade.
     * @return What the armor reduction will be after an upgrade.
     */
    public double ArmorReductionAfterUpgrade(){
        return armor.statsIfUpgraded();
    }

    /**
     * Viewed by the view to see how large the current
     * armor reduction is.
     * @return The current armor reduction.
     */
    public double CurrentArmorReduction(){
        return armor.currentDamageReduction();
    }

    /**
     * Used by the view to see the cost of upgrading the weapon.
     * @return The cost to upgrade the weapon.
     */
    public int getArmorUpgradeCost(){
        return armor.upgradeCost();
    }

    private void newPlayerMoneyAmount(int amountAfterTransaction) {
        player.setMoney(amountAfterTransaction);
    }

}
