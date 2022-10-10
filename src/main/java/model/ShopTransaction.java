package model;

import model.gameobjects.Player;

public class ShopTransaction {

    private int weaponStartPrize = 10;
    private int armorStartPrize = 10;
    private Player player;



    public ShopTransaction(Player player){
        this.player = player;
    }

    public void upgradeWeapon(){
       int cost = player.getWeapon().getLevel() * weaponStartPrize;
       if((player.getMoney() -cost) >= 0){
           newPlayerMoneyAmount(player.getMoney() - cost);
           player.getWeapon().levelUpWeapon();
       }
    }
    public void upgradeArmor(){
        int cost = player.getArmor().getLevel() * armorStartPrize;
        if((player.getMoney() - cost) >= 0){
            newPlayerMoneyAmount(player.getMoney() - cost);
            player.getArmor().upgradeArmor();
        }
    }
    public int getPlayerMoneyAmount(){
        return player.getMoney();
    }

    private void newPlayerMoneyAmount(int amountAfterTransaction) {
        player.setMoney(amountAfterTransaction);
    }

}
