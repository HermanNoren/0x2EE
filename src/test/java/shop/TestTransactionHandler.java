package shop;


import model.mapclasses.Tile;
import model.upgradables.armor.Armor;
import model.gameobjects.Player;
import model.helperclasses.TransactionHandler;

import model.upgradables.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestTransactionHandler {

    public Player player;
    public TransactionHandler transactionHandler;
    public Weapon weapon;
    public Armor armor;

    @BeforeEach
    void init(){
        player = new Player(48, 48, new Tile[0][0]);
        transactionHandler = new TransactionHandler(player);
        weapon = player.getWeapon();
        armor = player.getArmor();
    }


    /**
     * The first part tests just the interface IUpgradable
     */
    @Test
    void the_upgraded_armor_should_be_the_current_value_plus_one(){
        assertEquals(armor.currentStats() + 1, armor.statsIfUpgraded());
    }

    @Test
    void the_upgraded_weapon_should_be_the_current_value_plus_one(){
        assertEquals(weapon.currentStats() + 1, weapon.statsIfUpgraded());
    }
    @Test
    void the_weapon_stats_if_they_were_to_be_updated_should_match_the_updated_values(){
        int temporaryValue = weapon.statsIfUpgraded();
        weapon.upgrade();
        assertEquals(temporaryValue, weapon.currentStats());

    }
    @Test
    void the_armor_stats_if_they_were_to_be_updated_should_match_the_updated_values(){
        int temporaryValue = armor.statsIfUpgraded();
        armor.upgrade();
        assertEquals(temporaryValue, armor.currentStats());

    }
    @Test
    void weapon_stats_should_be_one_more_after_update(){
        int temporaryValue = weapon.currentStats();
        weapon.upgrade();
        assertEquals(temporaryValue + 1, weapon.currentStats());
    }
    @Test
    void armor_stats_should_be_one_more_after_update(){
        int temporaryValue = armor.currentStats();
        armor.upgrade();
        assertEquals(temporaryValue + 1, armor.currentStats());
    }

    @Test
    void weapon_should_get_twice_as_expensive_when_upgraded(){
        int priceBeforeUpgrade = weapon.upgradeCost();
        weapon.upgrade();
        int priceAfterUpgrade = weapon.upgradeCost();
        assertEquals(priceBeforeUpgrade * 2 , priceAfterUpgrade);

    }
    @Test
    void weapon_should_get_4_times_as_expensive_when_upgrade_3_times(){
        int priceBeforeUpgrade = weapon.upgradeCost();
        weapon.upgrade();
        weapon.upgrade();
        weapon.upgrade();
        int priceAfterUpgrade = weapon.upgradeCost();
        assertEquals(priceBeforeUpgrade * 4 , priceAfterUpgrade);
    }

    @Test
    void armor_should_get_twice_as_expensive_when_upgraded(){
        int priceBeforeUpgrade = armor.upgradeCost();
        armor.upgrade();
        int priceAfterUpgrade = armor.upgradeCost();
        assertEquals(priceBeforeUpgrade * 2, priceAfterUpgrade);
    }

    @Test
    void armor_should_get_4_times_as_expensive_when_upgrade_3_times(){
        int priceBeforeUpgrade = armor.upgradeCost();
        armor.upgrade();
        armor.upgrade();
        armor.upgrade();
        int priceAfterUpgrade = armor.upgradeCost();
        assertEquals(priceBeforeUpgrade * 4, priceAfterUpgrade);
    }


    /**
     * This parts tests the TransactionHandler.
     */
    @Test
    void getMoney_from_ShopTransaction_should_query_the_correct_amount_of_money_the_player_has(){
        player.setMoney(1000);
        int playerMoney = player.getMoney();
        int playerMoneyFromTransactionHandler= transactionHandler.getMoney();
        assertEquals(playerMoney, playerMoneyFromTransactionHandler);
    }
    @Test
    void getMoney_from_ShopTransaction_should_query_the_correct_amount_of_money_the_player_has_and_after_it_has_been_removed(){
        int playerMoney = player.getMoney();
        int playerMoneyFromTransactionHandler= transactionHandler.getMoney();
        assertEquals(playerMoney, playerMoneyFromTransactionHandler);
    }
    @Test
    void the_current_damage_from_the_weapon_should_be_the_same_in_the_IUpgradable_interface_and_the_TransactionHandler(){
        int currentDamageWeaponIUpgradable = weapon.currentStats();
        int currentDamageWeaponTransactionHandler = transactionHandler.getCurrentWeaponDamage();
        assertEquals(currentDamageWeaponIUpgradable, currentDamageWeaponTransactionHandler);
    }
    @Test
    void the_current_armor_reduction_from__armor_should_be_the_same_in_the_IUpgradable_interface_and_the_TransactionHandler(){
        int currentDamageWeaponIUpgradable = armor.currentStats();
        int currentDamageWeaponTransactionHandler = transactionHandler.getCurrentArmorReduction(); //have not implemented current
        assertEquals(currentDamageWeaponIUpgradable, currentDamageWeaponTransactionHandler);
    }
    //kuken
    @Test
    void the_current_damage_from_the_weapon_should_be_the_same_in_the_IUpgradable_interface_and_the_TransactionHandler_when_upgraded(){
        weapon.upgrade();
        int currentDamageWeaponIUpgradable = weapon.currentStats();
        int currentDamageWeaponTransactionHandler = transactionHandler.getCurrentWeaponDamage();
        assertEquals(currentDamageWeaponIUpgradable, currentDamageWeaponTransactionHandler);
    }
    @Test
    void the_current_armor_reduction_from_armor_should_be_the_same_in_the_IUpgradable_interface_and_the_TransactionHandler_when_upgraded(){
        armor.upgrade();
        int currentDamageWeaponIUpgradable = armor.currentStats();
        int currentDamageWeaponTransactionHandler = transactionHandler.getCurrentArmorReduction();
        assertEquals(currentDamageWeaponIUpgradable, currentDamageWeaponTransactionHandler);
    }
    @Test
    void the_upgrade_cost_of_the_weapon_should_match_the_one_through_the_IUpgradable_interface_and_the_one_through_TransactionHandler(){
        weapon.upgrade();
        int weaponUpgradeCostThroughTheInterface = weapon.upgradeCost();
        int weaponUpgradeCostThroughTheTransactionHandler = transactionHandler.getWeaponUpgradeCost();
        assertEquals(weaponUpgradeCostThroughTheInterface, weaponUpgradeCostThroughTheTransactionHandler);
    }
    @Test
    void the_upgrade_cost_of_the_armor_should_match_the_one_through_the_IUpgradable_interface_and_the_one_through_TransactionHandler(){
        armor.upgrade();
        int armorUpgradeCostThroughTheInterface = armor.upgradeCost();
        int armorUpgradeCostThroughTheTransactionHandler = transactionHandler.getArmorUpgradeCost();
        assertEquals(armorUpgradeCostThroughTheInterface, armorUpgradeCostThroughTheTransactionHandler);
    }

    @Test
    void the_players_money_should_be_updated_when_the_player_purchases_an_upgrade_weapon(){
        int playerMoneyBeforePurchase = player.getMoney();
        int weaponCost = weapon.upgradeCost();
        transactionHandler.upgradeWeapon();
        int playerMoneyAfterPurchase = player.getMoney();
        assertEquals(playerMoneyAfterPurchase, playerMoneyBeforePurchase - weaponCost);
    }
    @Test
    void the_players_money_should_be_updated_when_the_player_purchases_an_upgrade_armor(){
        int playerMoneyBeforePurchase = player.getMoney();
        int armorCost = armor.upgradeCost();
        transactionHandler.upgradeArmor();
        int playerMoneyAfterPurchase = player.getMoney();
        assertEquals(playerMoneyAfterPurchase, playerMoneyBeforePurchase - armorCost);
    }

}
