package shop;


import model.armor.Armor;
import model.gameobjects.Player;
import model.helperclasses.TransactionHandler;
import model.mapclasses.Terrain;
import model.weapons.Weapon;
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
        player = new Player(48, 48, new Terrain[0][0]);
        transactionHandler = new TransactionHandler(player);
        weapon = new Weapon();
        armor = new Armor();
    }

    @Test
    void test(){

    }

    @Test
    void test_if_the_method_returns_the_correct_upgraded_value_armor(){
        assertEquals(armor.currentStat() + 1, armor.statsIfUpgraded());
    }

    @Test
    void test_if_the_method_returns_the_correct_upgraded_value_weapon(){
        assertEquals(weapon.currentStat() + 1, weapon.statsIfUpgraded());
    }
}
