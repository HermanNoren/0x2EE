package model;

import model.armor.Armor;
import model.gameobjects.IUpgradable;
import model.gameobjects.Player;
import model.weapons.Weapon;

public class ShopTransaction {

    public int currentWeaponPrize = 10;
    public int currentArmorPrize = 10;
    private final Player player; //Kolla om final inte fuckade upp något



    public ShopTransaction(Player player){
        this.player = player;
    }
    public boolean isPurchasePossible(int currentPrize, IUpgradable current){
        return ((player.getMoney() - currentPrize * current.getCurrentLevel()) >= 0);

    }
    public void upgradeWeapon(){
       if(isPurchasePossible(currentWeaponPrize, getWeapon())){
           currentWeaponPrize *= getWeapon().getCurrentLevel();
           newPlayerMoneyAmount(player.getMoney() - currentWeaponPrize);
           getWeapon().levelUp();
       }
    }

    public void upgradeArmor(){
        if(isPurchasePossible(currentArmorPrize, getArmor())){
            this.currentArmorPrize *= getArmor().getCurrentLevel();
            newPlayerMoneyAmount(player.getMoney() - currentArmorPrize);
            getArmor().levelUp();
        }
    }
    public int getWeaponUpgradeCost(){
        return currentWeaponPrize * getWeapon().getCurrentLevel();
    }
    public int getArmorUpgradeCost(){
        return currentArmorPrize * getArmor().getCurrentLevel();
    }

    public int getPlayerMoneyAmount(){
        return player.getMoney();
    }
    public int getCurrentWeaponPrize(){
        return currentWeaponPrize;
    }
    public int getCurrentArmorPrize(){
        return currentArmorPrize;
    }

    public Armor getArmor(){
        return player.getArmor();
    }
    public Weapon getWeapon(){
        return player.getWeapon();
    }

    private void newPlayerMoneyAmount(int amountAfterTransaction) {
        player.setMoney(amountAfterTransaction);
    }

}
