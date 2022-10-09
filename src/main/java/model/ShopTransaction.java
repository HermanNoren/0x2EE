package model;

import model.gameobjects.Player;

public class ShopTransaction {
    private boolean canAffordArmor = false;
    private boolean canAffordWeapon = false;

    private int weaponStartPrize = 200;
    private int armorStartPrize = 200;
    private Player player;


    public ShopTransaction(Player player){
        this.player = player;
    }

    public void upgrade(String upgradePart){
        switch (upgradePart){
            case("armor")-> upgradeArmor();
            case("weapon")-> upgradeWeapon();
        }
    }
    public void upgradeWeapon(){
       int cost = player.weapon.getLevel() * weaponStartPrize;
       if((player.getMoney() -cost) <= 0){
           int amountAfterTransaction = player.getMoney() - cost;
           player.weapon.levelUpWeapon();
           calculateNewAmountOfMoney(amountAfterTransaction);
       }
       System.out.println("Upgraded Weapon!");
    }
    public void upgradeArmor(){
        System.out.println("Upgraded Armor!");

    }

    private void calculateNewAmountOfMoney(int amountAfterTransaction) {
        this.player.setMoney(amountAfterTransaction);
    }

}
