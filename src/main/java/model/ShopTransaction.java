package model;

import model.gameobjects.Player;

public class ShopTransaction {
    private boolean canAffordArmor = false;
    private boolean canAffordWeapon = false;

    private int weaponStartPrize = 200;
    private int armorStartPrize = 200;
    private Player player;


    public ShopTransaction(){
    }

    public void upgradeWeapon(){
       int cost = player.weapon.getLevel() * weaponStartPrize;
       if((player.getMoney() -cost) <= 0){
           int amountAfterTransaction = player.getMoney() - cost;
           player.weapon.levelUpWeapon();
           calculateNewAmountOfMoney(amountAfterTransaction);
       }
    }

    private void calculateNewAmountOfMoney(int amountAfterTransaction) {
        this.player.setMoney(amountAfterTransaction);
    }

}
